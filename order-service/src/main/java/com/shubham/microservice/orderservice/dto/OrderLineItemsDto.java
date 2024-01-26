package com.shubham.microservice.orderservice.dto;

import java.math.BigDecimal;
import java.util.List;

import com.shubham.microservice.orderservice.model.Order;
import com.shubham.microservice.orderservice.model.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {
	private Long id;
	private String skuCode;
	private BigDecimal price;
	private Integer  quantity;
}
