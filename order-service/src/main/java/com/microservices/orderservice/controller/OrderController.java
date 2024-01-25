package com.microservices.orderservice.controller;

import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @Retry(name = "Inventory")
    @CircuitBreaker(name="Inventory", fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "Inventory", fallbackMethod = "fallBackMethodTimeout")

    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));

    }

    public CompletableFuture<String> fallBackMethod(OrderRequest orderRequest, RuntimeException exception){
        System.out.println("Exception thrown : "+exception);
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong. Try again later.");
    }

    public CompletableFuture<String> fallBackMethodTimeout(OrderRequest orderRequest, Exception exception){
        System.out.println("Exception thrown : "+exception);
        return CompletableFuture.supplyAsync(() -> "Operation TimedOut..");
    }
}
