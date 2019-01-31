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
	
	@Override
	public boolean isValid(String name, Integer productAttributeId) {
		boolean isValid = false;
		List<ProductAttributeValues> productAttributeValues = attributesValueRepository.findByName(name);
		
	       int falg=0;
			for(int j=0; j<productAttributeValues.size();j++) {
				
				ProductAttributeValues pAttribvalue =  productAttributeValues.get(j);
				
				if(pAttribvalue.getProductAttributes().getId().equals(productAttributeId)) {
					falg++;
				}
				if(falg>=1) {
					isValid = true;
				}
			}
			 
		return isValid;
	}
	
}
