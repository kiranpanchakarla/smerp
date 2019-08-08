package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.PurchaseOrder;

public interface GoodsReceiptRepository  extends JpaRepository<GoodsReceipt, Integer> {
	
	@Query("SELECT r FROM GoodsReceipt r WHERE isActive=:isActive and plant.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt desc")
	List<GoodsReceipt> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds,@Param("secondApp") Boolean [] secondApp);
	
	GoodsReceipt findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:po and status!=:status order by createdAt desc")
	List<GoodsReceipt> findByListPoId(@Param("po") PurchaseOrder po,@Param("status") String status);
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:po and status = :status  order by createdAt desc")
	List<GoodsReceipt> findByApproveListPoId(@Param("po") PurchaseOrder po ,@Param("status") String status);
	
	@Query("SELECT r FROM GoodsReceipt r WHERE status = :status or status= :gStatus and plant.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt desc")
	List<GoodsReceipt> grApprovedList(@Param("status") String status,@Param("gStatus") String gStatus,@Param("plantIds") int[] plantIds,@Param("secondApp") Boolean [] secondApp);
	
	GoodsReceipt findByPoId(PurchaseOrder purchaseOrder);
	
	List<GoodsReceipt> findByDocNumber(String grDocNum);

}
