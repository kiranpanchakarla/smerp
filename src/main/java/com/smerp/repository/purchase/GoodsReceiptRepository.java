package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReceipt;

public interface GoodsReceiptRepository  extends JpaRepository<GoodsReceipt, Integer> {
	
	@Query("SELECT r FROM GoodsReceipt r WHERE isActive=:isActive order by createdAt asc")
	List<GoodsReceipt> findByIsActive(Boolean isActive);
	
	GoodsReceipt findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:id and status!=:status")
	List<GoodsReceipt> findByListPoId(int id,String status);
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:id and status = :status")
	List<GoodsReceipt> findByApproveListPoId(int id ,String status);
	
	@Query("SELECT r FROM GoodsReceipt r WHERE status = :status or status= :gStatus")
	List<GoodsReceipt> grApprovedList(String status,String gStatus);
	
	GoodsReceipt findByPoId(int id);

}
