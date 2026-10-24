package com.tecylab.order_api.controller;

import com.tecylab.order_api.client.ClientClient;
import com.tecylab.order_api.client.ProductClient;
import com.tecylab.order_api.dto.ClientResponse;
import com.tecylab.order_api.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ClientClient clientClient;
    private final ProductClient productClient;


    @GetMapping
    public Mono<List<ClientResponse>> retrieveAllClients() {
        // For demonstration purposes, we will retrieve a client with ID 1
        return clientClient.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Mono<ClientResponse> retrieveClientById(@PathVariable Long id){
        return clientClient.getClientById(id);
    }

    @GetMapping("/products")
    public Mono<List<ProductResponse>> retrieveAllProducts(){
           return productClient.getAllProducts();
    }

    @GetMapping("/list")
    public Mono<List<ProductResponse>> retrieveAllProductsByList(@RequestParam("ids")  List<Long> ids){
        return productClient.getProductsByList(ids);
    }

    @GetMapping("/products/{id}")
    public Mono<ProductResponse> retrieveProductsById(@PathVariable Long id){
        return productClient.getProductById(id);
    }

}
