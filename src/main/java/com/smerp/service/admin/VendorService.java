package com.smerp.service.admin;

import java.util.List;

import com.smerp.model.admin.Vendor;

public interface VendorService {

	Vendor getInfo(int id);
	
	List<Vendor> findAll();
	
	List<Vendor> findByIsActive();
	
	Vendor save(Vendor company);
	
	void delete(int id);

	List<String> findAllVendorNames();

	Vendor findByName(String vendorname);

	Vendor findById(int i);
}
