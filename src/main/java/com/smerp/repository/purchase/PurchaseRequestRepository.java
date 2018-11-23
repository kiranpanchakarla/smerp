package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.purchase.PurchaseRequest;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer> {

	@Query("SELECT P FROM PurchaseRequest P WHERE isActive=:isActive order by updatedAt desc")
	List<PurchaseRequest> findByIsActive(Boolean isActive);

	PurchaseRequest findTopByOrderByIdDesc();

}
