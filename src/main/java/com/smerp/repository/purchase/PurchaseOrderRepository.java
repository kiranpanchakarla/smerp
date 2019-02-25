package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("SELECT r FROM PurchaseOrder r WHERE isActive=:isActive order by createdAt desc")
	List<PurchaseOrder> findByIsActive(Boolean isActive);
	
	PurchaseOrder findTopByOrderByIdDesc();
	
	
	PurchaseOrder findByRfqId(RequestForQuotation rfq);
	
	//PurchaseOrder findByrfqId(Integer rfqId);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE status in (:status ,'Partially_Received') order by createdAt desc")
	List<PurchaseOrder> poApprovedList(String status);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE id=:id and status='Approved'")
	PurchaseOrder findByApproveId(Integer id);

	List<PurchaseOrder> findByDocNumber(String docNumber);
}
