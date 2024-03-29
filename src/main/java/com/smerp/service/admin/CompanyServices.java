package com.smerp.service.admin;

import java.util.List;

import com.smerp.model.admin.Company;

public interface CompanyServices {

	Company save(Company company);
	
	Company getInfo(int id);
	
	void delete(int id);
	
	List<Company> findAll();
	
	List<Company> findByIsActive();
	
	Company findByName(String name);
	
	Company findByEmailId(String emailId);
	
}
