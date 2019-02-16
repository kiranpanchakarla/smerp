package com.smerp.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Plant;
import com.smerp.model.admin.User;
import com.smerp.repository.admin.PlantRepository;
import com.smerp.service.master.PlantService;
import com.smerp.util.CheckUserPermissionUtil;

@Service
public class PlantServImpl implements PlantService {
	
	
	@Autowired
	PlantRepository plantRepository; 
	
	@Autowired
	CheckUserPermissionUtil checkUserPermissionUtil;

	@Override
	public List<Plant> findAll() {
		List<Plant> listPlant = null;
		User user = checkUserPermissionUtil.getUser();
		boolean checkWarhouse = checkUserPermissionUtil.checkUserPermission(user.getUserId(), 1, 9);
		if(checkWarhouse==true) {
			 listPlant = plantRepository.findAll(); 
		}else {
			 listPlant = plantRepository.findOneList(user.getPlant().getId());
		}
		return listPlant;
	}
	
	
	
	@Override
	public List<Plant> findPlantAll() {
		return plantRepository.findAll();
	}

	@Override
	public Plant save(Plant plant) {
		 
		return plantRepository.save(plant);
	}

	@Override
	public Plant findById(int id) {
		 
		return plantRepository.findById(id);
	}

	@Override
	public Plant getInfo(int id) {
		 
		return plantRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		plantRepository.deleteById(id);
		
	}

}
