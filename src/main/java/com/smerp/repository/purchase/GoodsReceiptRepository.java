package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.PurchaseOrder;

public interface GoodsReceiptRepository  extends JpaRepository<GoodsReceipt, Integer> {
	
	@Query("SELECT r FROM GoodsReceipt r WHERE isActive=:isActive order by createdAt desc")
	List<GoodsReceipt> findByIsActive(Boolean isActive);
	
	GoodsReceipt findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:po and status!=:status order by createdAt desc")
	List<GoodsReceipt> findByListPoId(PurchaseOrder po,String status);
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:po and status = :status order by createdAt desc")
	List<GoodsReceipt> findByApproveListPoId(PurchaseOrder po ,String status);
	
	@Query("SELECT r FROM GoodsReceipt r WHERE status = :status or status= :gStatus order by createdAt desc")
	List<GoodsReceipt> grApprovedList(String status,String gStatus);
	
	GoodsReceipt findByPoId(PurchaseOrder purchaseOrder);

}
