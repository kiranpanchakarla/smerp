package com.smerp.repository.inventorytransactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;

public interface InventoryGoodsTransferRepository extends JpaRepository<InventoryGoodsTransfer,Integer>{

	@Query("SELECT r FROM InventoryGoodsTransfer r WHERE isActive=:isActive and fromWarehouse.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt desc")
	List<InventoryGoodsTransfer> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds,@Param("secondApp") Boolean [] secondApp);
	
	InventoryGoodsTransfer findTopByOrderByIdDesc();
	
	List<InventoryGoodsTransfer> findByDocNumber(String docNum);
}
