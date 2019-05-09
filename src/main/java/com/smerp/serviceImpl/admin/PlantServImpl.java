package com.smerp.serviceImpl.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Plant;
import com.smerp.model.admin.User;
import com.smerp.repository.admin.PlantRepository;
import com.smerp.service.master.PlantService;
import com.smerp.serviceImpl.purchase.PurchaseRequestServiceImpl;
import com.smerp.util.CheckUserPermissionUtil;

@Service
public class PlantServImpl implements PlantService {
	
	
	@Autowired
	PlantRepository plantRepository; 
	
	@Autowired
	CheckUserPermissionUtil checkUserPermissionUtil;

	private static final Logger logger = LogManager.getLogger(PlantServImpl.class);
	
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
	public int[] findPlantIds() {
		List<Plant> list = findAll();
		int[] plantIds = new int[list.size()];
		for(int i=0; i< list.size(); i++) {
			plantIds[i] = list.get(i).getId();
		}
		return plantIds;
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



	@Override
	public Plant findByName(String name) {
		// TODO Auto-generated method stub
		return plantRepository.findByName(name);
	}

}
