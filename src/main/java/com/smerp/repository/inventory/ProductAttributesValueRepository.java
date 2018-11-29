package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.ProductAttributeValues;

public interface ProductAttributesValueRepository extends JpaRepository<ProductAttributeValues,Integer>{

	ProductAttributeValues findById(int id);
	
}
