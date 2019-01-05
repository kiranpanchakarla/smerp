package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.PurchaseOrder;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("SELECT r FROM PurchaseOrder r WHERE isActive=:isActive order by createdAt asc")
	List<PurchaseOrder> findByIsActive(Boolean isActive);
	
	PurchaseOrder findTopByOrderByIdDesc();
	
	
	PurchaseOrder findByRfqId(int id);
	
	//PurchaseOrder findByrfqId(Integer rfqId);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE status=:status order by createdAt asc")
	List<PurchaseOrder> poApprovedList(String status);


}
