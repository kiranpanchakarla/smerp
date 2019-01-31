package com.smerp.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.inventory.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

	ProductType findById(int id);
	
	ProductType findTopByOrderByIdDesc();
	
	List<ProductType> findAllByOrderByIdAsc();
	
	@Query("SELECT c FROM ProductType c WHERE LOWER(c.description) = LOWER(:description)")
	ProductType findByName(@Param("description") String description);
}
