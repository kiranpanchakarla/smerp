package com.smerp.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Plant;
import com.smerp.repository.admin.PlantRepository;
import com.smerp.service.master.PlantService;

@Service
public class PlantServImpl implements PlantService {
	
	
	@Autowired
	PlantRepository plantRepository; 

	@Override
	public List<Plant> findAll() {
		
		return plantRepository.findAll();
	}

}
