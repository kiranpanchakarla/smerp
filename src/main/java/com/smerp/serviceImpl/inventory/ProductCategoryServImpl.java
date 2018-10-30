package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.ProductCategory;
import com.smerp.repository.inventory.ProductCategoryRepository;
import com.smerp.service.inventory.ProductCategoryService;

@Service
public class ProductCategoryServImpl implements ProductCategoryService {
	
	@Autowired
	ProductCategoryRepository  productCategoryRepository;

	@Override
	public List<ProductCategory> findAll() {
		
		return productCategoryRepository.findAll();
	}

}
