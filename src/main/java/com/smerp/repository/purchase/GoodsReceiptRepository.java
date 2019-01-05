package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReceipt;

public interface GoodsReceiptRepository  extends JpaRepository<GoodsReceipt, Integer> {
	
	@Query("SELECT r FROM GoodsReceipt r WHERE isActive=:isActive order by createdAt asc")
	List<GoodsReceipt> findByIsActive(Boolean isActive);
	
	GoodsReceipt findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReceipt r WHERE poId=:id")
	List<GoodsReceipt> findByListPoId(int id);

}
