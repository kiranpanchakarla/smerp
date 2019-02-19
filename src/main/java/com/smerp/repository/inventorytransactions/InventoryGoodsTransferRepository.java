package com.smerp.repository.inventorytransactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;

public interface InventoryGoodsTransferRepository extends JpaRepository<InventoryGoodsTransfer,Integer>{

	@Query("SELECT r FROM InventoryGoodsTransfer r WHERE isActive=:isActive order by createdAt desc")
	List<InventoryGoodsTransfer> findByIsActive(Boolean isActive);
	
	InventoryGoodsTransfer findTopByOrderByIdDesc();
	
	List<InventoryGoodsTransfer> findByDocNumber(String docNum);
}
