package com.smerp.service.admin;


import com.smerp.model.admin.DashboardCount;

public interface DashboardCountService {

	DashboardCount findAll();
	
	DashboardCount findRFQCount();
	
	DashboardCount findPOCount();
	
	
}
