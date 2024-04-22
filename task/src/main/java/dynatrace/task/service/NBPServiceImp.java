package dynatrace.task.service;

import dynatrace.task.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class NBPServiceImp implements NBPService{
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;

    public NBPServiceImp(WebClient localApiClient){
        this.localApiClient = localApiClient;
    }

    @Override
    public AverageRate getAverageExchangeRate(String code, LocalDate date) {
        TableA tableResponse = localApiClient
                .get()
                .uri("a/"+code+"/"+date+"?format=json")
                .retrieve()
                .bodyToMono(TableA.class)
                .block(REQUEST_TIMEOUT);
        return tableResponse.getRates().get(0);
    }

    @Override
    public MinMaxRate getMinMaxExchangeRate(String code, int n) {
        if (n > 255) {
            throw new IllegalArgumentException("Value of n must be less than or equal to 255.");
        }
        TableA tableResponse = localApiClient
                .get()
                .uri("a/"+code+"/last/"+n+"?format=json")
                .retrieve()
                .bodyToMono(TableA.class)
                .block(REQUEST_TIMEOUT);
        if (tableResponse != null && tableResponse.getRates() != null && !tableResponse.getRates().isEmpty()) {
            AverageRate minRate = tableResponse.getRates().get(0);
            AverageRate maxRate = tableResponse.getRates().get(0);
            for (AverageRate rate : tableResponse.getRates()) {
                if (rate.getMid() < minRate.getMid()) {
                    minRate = rate;
                }
                if (rate.getMid() > maxRate.getMid()) {
                    maxRate = rate;
                }
            }
            return new MinMaxRate(minRate, maxRate);
        }
        return null;

    }

    @Override
    public BidAskRateResponse getBiggestDifferenceRate(String code, int n) {
        if (n > 255) {
            throw new IllegalArgumentException("Value of n must be less than or equal to 255.");
        }
        TableC tableResponse = localApiClient
                .get()
                .uri("c/"+code+"/last/"+n+"?format=json")
                .retrieve()
                .bodyToMono(TableC.class)
                .block(REQUEST_TIMEOUT);
        if (tableResponse != null && tableResponse.getRates() != null && !tableResponse.getRates().isEmpty()) {
            BidAskRateResponse biggestDiffRates = new BidAskRateResponse();
            biggestDiffRates.setDifference(Double.NEGATIVE_INFINITY);

            for (BidAskRate rate : tableResponse.getRates()) {
                double difference = rate.getAsk() - rate.getBid();
                if (difference >= biggestDiffRates.getDifference()) {
                    biggestDiffRates = new BidAskRateResponse();
                    biggestDiffRates.setNo(rate.getNo());
                    biggestDiffRates.setEffectiveDate(rate.getEffectiveDate());
                    biggestDiffRates.setBid(rate.getBid());
                    biggestDiffRates.setAsk(rate.getAsk());
                    biggestDiffRates.setDifference(difference);
                }
            }
            return biggestDiffRates;
        }

        return null;
    }
}
