package com.shubham.microservice.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubham.microservice.inventoryservice.model.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

//	Optional<Inventory> findBySkuCode(String skuCode);

	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}
