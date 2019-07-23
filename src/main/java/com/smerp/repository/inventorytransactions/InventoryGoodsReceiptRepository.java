package com.smerp.repository.inventorytransactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;

public interface InventoryGoodsReceiptRepository extends JpaRepository<InventoryGoodsReceipt,Integer> {

	@Query("SELECT r FROM InventoryGoodsReceipt r WHERE isActive=:isActive and plant.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt Desc")
	List<InventoryGoodsReceipt> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds,@Param("secondApp") Boolean [] secondApp);
	
	InventoryGoodsReceipt findTopByOrderByIdDesc();

	List<InventoryGoodsReceipt> findByDocNumber(String docNum);
}
