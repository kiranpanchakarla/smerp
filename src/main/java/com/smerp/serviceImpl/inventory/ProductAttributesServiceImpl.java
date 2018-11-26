package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.ProductAttributes;
import com.smerp.repository.inventory.ProductAttributesRepository;
import com.smerp.service.inventory.ProductAttributesService;
@Service
public class ProductAttributesServiceImpl implements ProductAttributesService {

	@Autowired
	ProductAttributesRepository productAttributesRepository;
	
	@Override
	public List<ProductAttributes> findAll() {
		 
		return productAttributesRepository.findAll();
	}

	@Override
	public ProductAttributes save(ProductAttributes productAttributes) {
		 
		return productAttributesRepository.save(productAttributes);
	}

	@Override
	public ProductAttributes findById(int id) {
		 
		return productAttributesRepository.findById(id);
	}

	@Override
	public ProductAttributes getInfo(int id) {
		return productAttributesRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		productAttributesRepository.deleteById(id);

	}

}
