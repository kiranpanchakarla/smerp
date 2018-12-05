package com.smerp.service.inventory;

import com.smerp.model.inventory.UomCategory;
import java.util.List;

public interface UomCatergoryService {

	List<UomCategory> findAll();
	
	UomCategory save(UomCategory uomCategory);
	
	UomCategory findById(int id);
	
	UomCategory getInfo(int id);
	
	void delete(int id);

	UomCategory findByName(String name);
}
