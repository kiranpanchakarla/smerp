package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.RequestForQuotation;

public interface RequestForQuotationRepository extends JpaRepository<RequestForQuotation, Integer> {

}
