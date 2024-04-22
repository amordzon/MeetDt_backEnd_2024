package dynatrace.task.service;

import dynatrace.task.dto.MinMaxRate;
import dynatrace.task.dto.Rates;
import dynatrace.task.dto.TableA;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class NBPServiceImp implements NBPService{
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;

    public NBPServiceImp(WebClient localApiClient){
        this.localApiClient = localApiClient;
    }

    @Override
    public Rates getAverageExchangeRate(String code, LocalDate date) {
        TableA tableResponse = localApiClient
                .get()
                .uri("rates/a/"+code+"/"+date+"?format=json")
                .retrieve()
                .bodyToMono(TableA.class)
                .block(REQUEST_TIMEOUT);
        return tableResponse.getRates().get(0);
    }

    @Override
    public MinMaxRate getMinMaxExchangeRate(String code, int n) {
        TableA tableResponse = localApiClient
                .get()
                .uri("rates/a/"+code+"/last/"+n+"?format=json")
                .retrieve()
                .bodyToMono(TableA.class)
                .block(REQUEST_TIMEOUT);
        if (tableResponse != null && tableResponse.getRates() != null && !tableResponse.getRates().isEmpty()) {
            Rates minRate = tableResponse.getRates().get(0);
            Rates maxRate = tableResponse.getRates().get(0);
            for (Rates rate : tableResponse.getRates()) {
                if (rate.getMid() < minRate.getMid()) {
                    minRate = rate;
                }
                if (rate.getMid() > maxRate.getMid()) {
                    maxRate = rate;
                }
            }
            return new MinMaxRate(minRate, maxRate);
        } else {
            return null;
        }
    }
}
