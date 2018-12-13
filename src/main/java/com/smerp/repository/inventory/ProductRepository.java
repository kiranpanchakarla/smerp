package com.smerp.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.inventory.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT P FROM Product P WHERE isActive=:isActive order by createdAt asc")
	List<Product> findByIsActive(Boolean isActive);

	@Query("SELECT description FROM Product")
	List<String> findByDescription();
	
	Product findByDescription(String name);
	
	/*@Query("SELECT productNo FROM Product WHERE serviceOrProduct=:product and isActive=true ")*/
	@Query("SELECT productNo FROM Product WHERE isActive=true ")
	List<String> findAllProductNamesByProduct(@Param("product") String product);
	
	Product findByProductNo(String name);
}
