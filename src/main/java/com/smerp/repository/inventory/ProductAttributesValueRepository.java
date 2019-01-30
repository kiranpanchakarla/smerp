package com.smerp.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.ProductAttributeValues;

public interface ProductAttributesValueRepository extends JpaRepository<ProductAttributeValues,Integer>{

	ProductAttributeValues findById(int id);
	
	@Query("SELECT c FROM ProductAttributeValues c WHERE LOWER(c.attributeValue) = LOWER(:name) ")
	List<ProductAttributeValues> findByName(@Param("name") String name);
}
