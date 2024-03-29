package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("SELECT r FROM PurchaseOrder r WHERE isActive=:isActive and plant.id in (:plantIds) order by createdAt desc")
	List<PurchaseOrder> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds);
	
	PurchaseOrder findTopByOrderByIdDesc();
	
	PurchaseOrder findByRfqId(RequestForQuotation rfq);
	
	@Query("SELECT r FROM PurchaseOrder r WHERE rfqId=:rfqId and status NOT IN ('Rejected')")
	PurchaseOrder findByPONotRejected(@Param("rfqId") RequestForQuotation rfqId);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE status in (:status ,'Partially_Received') and plant.id in (:plantIds) order by createdAt desc")
	List<PurchaseOrder> poApprovedList(@Param("status") String status,@Param("plantIds") int[] plantIds);
	
	
	@Query("SELECT r FROM PurchaseOrder r WHERE id=:id and status='Approved'")
	PurchaseOrder findByApproveId(@Param("id") Integer id);

	List<PurchaseOrder> findByDocNumber(String docNumber);
}
