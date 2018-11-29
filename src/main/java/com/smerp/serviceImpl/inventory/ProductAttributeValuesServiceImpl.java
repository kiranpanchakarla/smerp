package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.ProductAttributeValues;
import com.smerp.repository.inventory.ProductAttributesValueRepository;
import com.smerp.service.inventory.ProductAttributeValuesService;

@Service
public class ProductAttributeValuesServiceImpl implements ProductAttributeValuesService {

	@Autowired
	ProductAttributesValueRepository attributesValueRepository;
	
	@Override
	public List<ProductAttributeValues> findAll() {
		return attributesValueRepository.findAll();
	}

	@Override
	public ProductAttributeValues save(ProductAttributeValues productAttributeValues) {
		return attributesValueRepository.save(productAttributeValues);
	}

	@Override
	public ProductAttributeValues findById(int id) {
		return attributesValueRepository.findById(id);
	}

	@Override
	public ProductAttributeValues getInfo(int id) {
		return attributesValueRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		attributesValueRepository.deleteById(id);
	}

}
