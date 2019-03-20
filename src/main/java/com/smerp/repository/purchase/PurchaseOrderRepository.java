package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("SELECT r FROM PurchaseOrder r WHERE isActive=:isActive and plant.id in (:plantIds) order by createdAt desc")
	List<PurchaseOrder> findByIsActive(Boolean isActive, int[] plantIds);
	
	PurchaseOrder findTopByOrderByIdDesc();
	
	
	PurchaseOrder findByRfqId(RequestForQuotation rfq);
	
	//PurchaseOrder findByrfqId(Integer rfqId);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE status in (:status ,'Partially_Received') and plant.id in (:plantIds) order by createdAt desc")
	List<PurchaseOrder> poApprovedList(String status, int[] plantIds);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE id=:id and status='Approved'")
	PurchaseOrder findByApproveId(Integer id);

	List<PurchaseOrder> findByDocNumber(String docNumber);
}
