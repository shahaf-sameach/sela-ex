package com.sela.hotelservice.service;

import com.sela.hotelservice.data.PriceResponse;
import com.sela.hotelservice.data.Provider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class HotelService {

    @Autowired
    private ProviderService providerService;

    @Value("${application.timeout:1000}")
    private Long timeout;

    public Flux<PriceResponse> getAll(String hotelName) {
        return Flux.fromStream(providerService.getProviders().stream())
                .flatMap(provider -> getPrice(provider, hotelName));
    }

    private Mono<PriceResponse> getPrice(Provider provider, String hotelName) {
        WebClient client = WebClient.create(provider.getUrl());

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/hotel/price").queryParam("hotelName", hotelName).build())
                .retrieve()
                .bodyToMono(Integer.class)
                .map(price -> PriceResponse.success(provider.getName(), hotelName, price))
                .timeout(Duration.of(timeout, ChronoUnit.MILLIS))
                .onErrorResume(e -> {
                    log.error("Error", e);
                    return Mono.just(PriceResponse.failure(provider.getName(), hotelName));
                });
    }
}
