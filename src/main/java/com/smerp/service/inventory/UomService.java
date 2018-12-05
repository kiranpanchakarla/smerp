package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.Uom;

public interface UomService {

	List<Uom> findAll();
	
	List<Uom> uomList(int uomCategoryId);

	Uom save(Uom uom);
	
	Uom findById(int id);
	
	Uom getInfo(int id);
	
	void delete(int id);
	
	Uom findByName(String name);

}
