package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.TaxCode;

public interface TaxCodeRepository  extends JpaRepository<TaxCode, Integer> {
	 List<TaxCode> findAllByOrderByTaxCodeAsc();
}
