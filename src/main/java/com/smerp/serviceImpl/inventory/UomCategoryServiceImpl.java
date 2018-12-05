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


	@Override
	public UomCategory save(UomCategory uomCategory) {
		return uomCategoryRepository.save(uomCategory);
	}


	@Override
	public UomCategory findById(int id) {
		return uomCategoryRepository.findById(id);
	}


	@Override
	public UomCategory getInfo(int id) {
		return uomCategoryRepository.findById(id);
	}


	@Override
	public void delete(int id) {
		uomCategoryRepository.deleteById(id);
		
	}


	@Override
	public UomCategory findByName(String name) {
		return uomCategoryRepository.findByName(name);
	}
	

}
