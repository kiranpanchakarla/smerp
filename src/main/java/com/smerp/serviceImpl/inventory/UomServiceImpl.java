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


	@Override
	public Uom save(Uom uom) {
		 
		return uomRepository.save(uom);
	}


	@Override
	public Uom findById(int id) {
		 
		return uomRepository.findById(id);
	}


	@Override
	public Uom getInfo(int id) {
		 
		return uomRepository.findById(id);
	}


	@Override
	public void delete(int id) {
		uomRepository.deleteById(id);
		
	}


	@Override
	public Uom findByName(String name) {
		// TODO Auto-generated method stub
		return uomRepository.findByName(name);
	}

	
	/*public List<Uom> findById(Integer id) {
		
		return uomRepository.findById(id);
	}*/

	
	

}
