package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.Product;

public interface ProductService {

	Product save(Product product);

	List<Product> productList();
	
	List<Product> findByIsActive(Boolean isActive);
	
	List<Product> findAll();

	Product getInfo(int id);

	Product update(Product product);

	Product delete(int id);

	Product findByProductNo(String productNo);

	/*void delete(int parseInt);*/

}
