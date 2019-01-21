package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.ProductType;

public interface ProductTypeService {

	List<ProductType> findAll();
	
	ProductType save(ProductType productType);
    
	ProductType findbyId(int id);
	
	void delete(int id);
	
	ProductType getInfo(int id);
	
	ProductType findLastCodeNumber();
	
}
