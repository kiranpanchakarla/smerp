package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.ProductAttributes;

public interface ProductAttributesService {
	
	List<ProductAttributes> findAll();
	
	ProductAttributes save(ProductAttributes productAttributes);
	
	ProductAttributes findById(int id);
	
	ProductAttributes getInfo(int id);
	
	void delete(int id);
	
	ProductAttributes findByName(String name);
	

}
