package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.Product;
import com.smerp.model.inventory.ProductType;

public interface ProductService {

	Product save(Product product);

	List<Product> productList();
	
	List<Product> findByIsActive(Boolean isActive);
	
	List<Product> findAll();

	Product getInfo(int id);

	Product update(Product product);

	Product delete(int id);

	Product findByProductNo(String productNo);

	List<Product> findByIsActive();

	List<String> findAllProductNames();

	Product findByDescription(String name);

	List<String> findAllProductNamesByProduct(String product);
	
	List<String> findAllProductDescription(String product);

	Product findByproductNo(String name);
	
	Product findLastCodeNumber(String productGroup);

	

}
