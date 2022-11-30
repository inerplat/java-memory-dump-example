package com.example.demo.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CommonClient {
    private final WebClient webClient;
    private CommonClient() {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
        webClient = WebClient.builder().exchangeStrategies(exchangeStrategies).build();
    }

    public Mono<String> clientCycle(String name) {
        return webClient.get()
                .uri("http://localhost:8080/client/cycle/%s".formatted(name))
                .retrieve()
                .bodyToMono(String.class);
    }
}
