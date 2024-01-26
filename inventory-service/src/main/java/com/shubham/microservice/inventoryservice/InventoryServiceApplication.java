package com.shubham.microservice.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {		
//		return retu->{
//			Inventory inventory=Inventory.builder()
//					.skuCode("iphone_13")
//					.quantity(100)
//					.build();
//			
//			Inventory inventory1=Inventory.builder()
//					.skuCode("iphone_13_Red")
//					.quantity(1)
//					.build();
//			
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//			
//		};
//	}

	

}
