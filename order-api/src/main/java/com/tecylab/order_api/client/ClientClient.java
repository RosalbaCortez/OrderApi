package com.tecylab.order_api.client;

import com.tecylab.order_api.dto.ClientResponse;
import com.tecylab.order_api.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientClient {

    private final WebClient.Builder webClientBuilder;
    @Value("${client.service.url}")
    private String clientServiceUrl;
    public Mono<ClientResponse> getClientById(Long clientId) {
        WebClient webClient = webClientBuilder.baseUrl(clientServiceUrl).build();

        return webClient
                .get()
                .uri("/{id}", clientId)
                .retrieve()
                .bodyToMono(ClientResponse.class);
    }

    public Mono<List<ClientResponse>> getAllClients(){
        WebClient webClient=webClientBuilder.baseUrl(clientServiceUrl).build();
        return webClient.get().uri("/all").retrieve().bodyToFlux(ClientResponse.class).collectList();
    }
}
