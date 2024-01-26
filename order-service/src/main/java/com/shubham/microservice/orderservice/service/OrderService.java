package com.shubham.microservice.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.shubham.microservice.orderservice.dto.InventoryResponse;
import com.shubham.microservice.orderservice.dto.OrderLineItemsDto;
import com.shubham.microservice.orderservice.dto.OrderRequest;
import com.shubham.microservice.orderservice.model.Order;
import com.shubham.microservice.orderservice.model.OrderLineItems;
import com.shubham.microservice.orderservice.orderrepository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;

	private final WebClient.Builder webClient;

	public String placeOrder(OrderRequest orderRequest) {

		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderLineItems> list = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();

		order.setOrderlineItemsList(list);

		List<String> SkuCodes = order.getOrderlineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
		// calling inventory service to check whther product is in stock or not
		// stock
//		webClient.get("http://localhost:8082/api/inventory")
		InventoryResponse []inventoryResponseArray = webClient.build()
				.get()
				.uri("http://inventory-service/api/inventory",uriBuilder ->uriBuilder.queryParam("skuCode",SkuCodes).build() )
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		System.out.println(inventoryResponseArray);
		boolean result = Arrays.stream(inventoryResponseArray)
				.allMatch(InventoryResponse::isInStock);
		
		if (result) {
			orderRepository.save(order);
			return "Order Placed Successfully";
		}else {
			throw new IllegalArgumentException("Product is not in stock , please try again after some time!!!");
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderlineitemsdto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderlineitemsdto.getPrice());
		orderLineItems.setQuantity(orderlineitemsdto.getQuantity());
		orderLineItems.setSkuCode(orderlineitemsdto.getSkuCode());

		return orderLineItems;
	}
}
