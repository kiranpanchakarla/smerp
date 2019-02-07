package com.smerp.repository.inventorytransactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;

public interface InventoryGoodsReceiptRepository extends JpaRepository<InventoryGoodsReceipt,Integer> {

	@Query("SELECT r FROM InventoryGoodsReceipt r WHERE isActive=:isActive order by createdAt asc")
	List<InventoryGoodsReceipt> findByIsActive(Boolean isActive);
	
	InventoryGoodsReceipt findTopByOrderByIdDesc();
	
}
