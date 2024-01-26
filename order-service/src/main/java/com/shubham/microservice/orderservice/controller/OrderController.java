package com.shubham.microservice.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.microservice.orderservice.dto.OrderRequest;
import com.shubham.microservice.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@TimeLimiter(name="inventory")
	@Retry(name="inventory")
	@CircuitBreaker(name ="inventory" ,fallbackMethod ="fallBackMethod")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
	}
	
	public CompletableFuture<String> fallBackMethod(OrderRequest orderRequest,RuntimeException exception) {
		return CompletableFuture.supplyAsync(()-> "Oops !!! Something Went Wrong , Please try after some time ");
	}
}
