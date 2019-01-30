package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.ProductAttributes;

public interface ProductAttributesRepository extends JpaRepository<ProductAttributes,Integer>{

	ProductAttributes findById(int id);
	
	@Query("SELECT c FROM ProductAttributes c WHERE LOWER(c.attributeName) = LOWER(:name)")
	ProductAttributes findByName(@Param("name") String name);
	
}
