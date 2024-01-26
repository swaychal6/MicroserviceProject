package com.shubham.microservice.productservice.dto;

import java.math.BigDecimal;

import com.shubham.microservice.productservice.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {
	
	private String name;
	private String description;
	private BigDecimal price;
}
