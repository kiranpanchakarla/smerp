package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.Uom;

public interface UomService {

	List<Uom> findAll();
	
	List<Uom> uomList(int uomCategoryId);

	/*List<Uom> findById(Integer id);*/

}
