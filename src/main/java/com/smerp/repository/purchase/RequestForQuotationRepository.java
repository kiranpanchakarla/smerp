package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.RequestForQuotation;

public interface RequestForQuotationRepository extends JpaRepository<RequestForQuotation, Integer> {
	
	@Query("SELECT r FROM RequestForQuotation r WHERE isActive=:isActive order by updatedAt desc")
	List<RequestForQuotation> findByIsActive(Boolean isActive);
	
	RequestForQuotation findTopByOrderByIdDesc();

}
