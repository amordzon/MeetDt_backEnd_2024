package dynatrace.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public WebClient localApiClient() {
        return WebClient.create("http://api.nbp.pl/api/exchangerates/rates/");
    }
}
