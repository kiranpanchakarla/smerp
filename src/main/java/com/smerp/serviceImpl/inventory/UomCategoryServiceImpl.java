package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.UomCategory;
import com.smerp.repository.inventory.UomCategoryRepository;
import com.smerp.service.inventory.UomCatergoryService;


@Service
public class UomCategoryServiceImpl implements UomCatergoryService {

	
	@Autowired
	private UomCategoryRepository  uomCategoryRepository;
	
	
	@Override
	public List<UomCategory> findAll() {
		
		return uomCategoryRepository.findAll();
	}
	

}
