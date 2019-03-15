package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.smerp.model.purchase.PurchaseRequest;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer> {
    String plantN= "1";
	@Query("SELECT P FROM PurchaseRequest P WHERE isActive=:isActive and plant.id in (:plantIds) order by createdAt desc" )
	List<PurchaseRequest> findByIsActive(Boolean isActive, int[] plantIds);
	
	@Query("SELECT P FROM PurchaseRequest P WHERE status=:status order by createdAt desc")
	List<PurchaseRequest> prApprovedList(String status);

	PurchaseRequest findTopByOrderByIdDesc();
	
	List<PurchaseRequest> findByDocNumber(String docNumber);

}
