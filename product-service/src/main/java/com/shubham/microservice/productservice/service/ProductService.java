package com.shubham.microservice.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.microservice.productservice.dto.ProductRequest;
import com.shubham.microservice.productservice.dto.ProductResponse;
import com.shubham.microservice.productservice.model.Product;
import com.shubham.microservice.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product=Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("product {} is saved",product.getId());
	}

	public List<ProductResponse> getAllProduct() {
		// TODO Auto-generated method stub
			List<Product> products = productRepository.findAll();
			
			return	products.stream().map(this::mapToProductResponse).toList();
			
			
	}

	private ProductResponse mapToProductResponse(Product product) {
		// TODO Auto-generated method stub
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();				
	}
}
