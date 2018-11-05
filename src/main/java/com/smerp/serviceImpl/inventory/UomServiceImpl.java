package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.Uom;
import com.smerp.repository.inventory.UomRepository;
import com.smerp.service.inventory.UomService;

@Service
public class UomServiceImpl implements UomService {
	
	
	@Autowired
	private UomRepository uomRepository;

	@Override
	public List<Uom> findAll() {
		
		return uomRepository.findAll();
	}

	
	public List<Uom> uomList(int id) {
		return uomRepository.findByuomCategoryId(id);
	}

	
	/*public List<Uom> findById(Integer id) {
		
		return uomRepository.findById(id);
	}*/

	
	

}
