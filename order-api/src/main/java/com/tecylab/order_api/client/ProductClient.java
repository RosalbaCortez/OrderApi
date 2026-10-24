package com.tecylab.order_api.client;

import com.tecylab.order_api.dto.ProductResponse;
import com.tecylab.order_api.error.ProductNotFoudException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient.Builder webClientBuilder;
    @Value("${product.service.url}")
    private String productServiceUrl;



    public Mono<ProductResponse> getProductById(Long productId) {
        WebClient webClient = webClientBuilder.baseUrl(productServiceUrl).build();



        return  webClient.get()
                .uri("/api/products/{id}", productId)
                .retrieve()
                .onStatus(status -> status.value() == 404, response ->
                        Mono.error(new ProductNotFoudException("El producto no existe en el servicio de productos"))
                )
                .bodyToMono(ProductResponse.class);

    }

    public Mono<List<ProductResponse>> getProductsByList(List<Long> productsIDs) {
   WebClient webClient=webClientBuilder.baseUrl(productServiceUrl).build();
    return webClient.get().uri(uriBuilder -> uriBuilder.path("/list").queryParam("ids", productsIDs).build())
            .retrieve().bodyToFlux(ProductResponse.class).collectList();
    }



    public Mono<List<ProductResponse>> getAllProducts(){
    WebClient webClient=webClientBuilder.baseUrl(productServiceUrl).build();
    return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/all").build())
            .retrieve().bodyToFlux(ProductResponse.class).collectList();

    }
}