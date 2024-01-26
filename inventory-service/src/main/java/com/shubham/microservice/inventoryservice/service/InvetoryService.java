package com.shubham.microservice.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shubham.microservice.inventoryservice.dto.InventoryResponse;
import com.shubham.microservice.inventoryservice.model.Inventory;
import com.shubham.microservice.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvetoryService {

	private final InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	@SneakyThrows
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		log.info("Wait started");
		Thread.sleep(10000);
		log.info("wait ended");
		List<InventoryResponse> list = inventoryRepository.findBySkuCodeIn(skuCode)
				.stream()
				.map(inventory -> InventoryResponse.builder()
						.skuCode(inventory.getSkuCode())
						.isInStock(inventory.getQuantity() > 0)
						.build()).toList();
		System.out.println(list);
		return list;
	}

}
