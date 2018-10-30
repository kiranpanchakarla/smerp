package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.ProductType;
import com.smerp.repository.inventory.ProductTypeRepository;
import com.smerp.service.inventory.ProductTypeService;

@Service
public class ProductTypeServImpl implements ProductTypeService {
	
	@Autowired
	ProductTypeRepository  productTypeRepository;

	@Override
	public List<ProductType> findAll() {
		
		return productTypeRepository.findAll();
	} 

}
