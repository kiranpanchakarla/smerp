package com.smerp.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smerp.model.inventory.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT P FROM Product P WHERE isActive=:isActive order by updatedAt desc")
	List<Product> findByIsActive(Boolean isActive);

	Product findByProductNo(String productNo);
}
