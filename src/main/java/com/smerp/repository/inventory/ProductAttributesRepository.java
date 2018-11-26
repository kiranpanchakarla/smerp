package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.ProductAttributes;

public interface ProductAttributesRepository extends JpaRepository<ProductAttributes,Integer>{

	ProductAttributes findById(int id);
	
}
