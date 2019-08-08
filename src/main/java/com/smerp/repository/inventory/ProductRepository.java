package com.smerp.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.inventory.Product;
import com.smerp.model.inventory.ProductType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT P FROM Product P WHERE isActive=:isActive order by createdAt asc")
	List<Product> findByIsActive(@Param("isActive") Boolean isActive);

	@Query("SELECT description FROM Product")
	List<String> findByDescription();
	
	Product findByDescription(String name);
	
	/*@Query("SELECT productNo FROM Product WHERE serviceOrProduct=:product and isActive=true ")*/
	@Query("SELECT productNo FROM Product WHERE isActive=true order by createdAt asc")
	List<String> findAllProductNamesByProduct(@Param("product") String product);
	
	@Query("SELECT description FROM Product WHERE isActive=true order by createdAt asc")
	List<String> findAllProductDescription(@Param("product") String product);
	
	Product findByProductNo(String name);
	
	//@Query("SELECT 1 * FROM Product  WHERE productGroup.productName=:name order by createdAt asc LIMIT 1")
	/*@Query(value="SELECT 1 * FROM Product  WHERE productGroup.productName=:name order by createdAt DESC LIMIT 1", nativeQuery = true)
	Product findProductGroupByProductCode(String name);*/
}
