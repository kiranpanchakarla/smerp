package com.smerp.service.master;

import java.util.List;

import com.smerp.model.admin.Plant;

public interface PlantService {

	List<Plant> findAll();
	
	List<Plant> findPlantAll();
	
	Plant save(Plant plant);
	
	Plant findById(int id);
	
	Plant getInfo(int id);
	
	void delete(int id);
	
	Plant findByName(String name);
	
	int[] findPlantIds();
	

}
