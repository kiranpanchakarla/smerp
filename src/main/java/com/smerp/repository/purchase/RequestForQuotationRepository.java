package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;

public interface RequestForQuotationRepository extends JpaRepository<RequestForQuotation, Integer> {
	
	@Query("SELECT r FROM RequestForQuotation r WHERE isActive=:isActive order by createdAt desc")
	List<RequestForQuotation> findByIsActive(Boolean isActive);
	
	@Query("SELECT r FROM RequestForQuotation r WHERE status=:status order by createdAt desc")
	List<RequestForQuotation> rfqApprovedList(String status);
	
	RequestForQuotation findTopByOrderByIdDesc();
	
	RequestForQuotation findByPurchaseReqId(PurchaseRequest purchaseReqId);
	
	
}
