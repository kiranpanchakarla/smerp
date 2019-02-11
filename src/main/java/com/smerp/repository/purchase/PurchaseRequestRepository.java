package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.smerp.model.purchase.PurchaseRequest;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer> {

	@Query("SELECT P FROM PurchaseRequest P WHERE isActive=:isActive order by createdAt desc")
	List<PurchaseRequest> findByIsActive(Boolean isActive);
	
	@Query("SELECT P FROM PurchaseRequest P WHERE status=:status order by createdAt desc")
	List<PurchaseRequest> prApprovedList(String status);

	PurchaseRequest findTopByOrderByIdDesc();
	
	

}
