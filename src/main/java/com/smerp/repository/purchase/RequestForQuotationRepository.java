package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.admin.Company;
import com.smerp.model.inventory.RequestForQuotation;

public interface RequestForQuotationRepository extends JpaRepository<RequestForQuotation, Integer> {
	
	@Query("SELECT r FROM RequestForQuotation r WHERE isActive=:isActive order by createdAt asc")
	List<RequestForQuotation> findByIsActive(Boolean isActive);
	
	RequestForQuotation findTopByOrderByIdDesc();
	
	RequestForQuotation findByPurchaseReqId(Integer purchaseReqId);
	
	
}
