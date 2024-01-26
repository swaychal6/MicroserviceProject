package com.shubham.microservice.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.microservice.productservice.dto.ProductRequest;
import com.shubham.microservice.productservice.dto.ProductResponse;
import com.shubham.microservice.productservice.model.Product;
import com.shubham.microservice.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProduct(){
		return productService.getAllProduct();
	}
	
	
	
}
