package dynatrace.task.service;

import dynatrace.task.dto.TableA;
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
    public double getAverageExchangeRate(String code, LocalDate date) {
        TableA tableResponse = localApiClient
                .get()
                .uri("rates/a/"+code+"/"+date+"?format=json")
                .retrieve()
                .bodyToMono(TableA.class)
                .block(REQUEST_TIMEOUT);
        return tableResponse != null ? tableResponse.getRates().get(0).getMid() : 0.0;
    }
}
