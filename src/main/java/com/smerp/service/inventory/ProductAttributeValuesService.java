package com.smerp.service.inventory;

import java.util.List;
import com.smerp.model.inventory.ProductAttributeValues;

public interface ProductAttributeValuesService {

	List<ProductAttributeValues> findAll();
	
	ProductAttributeValues save(ProductAttributeValues productAttributeValues);
	
	ProductAttributeValues findById(int id);
	
	ProductAttributeValues getInfo(int id);
	
	void delete(int id);
	
	boolean isValid(String name,Integer productAttribId); 
	
}
