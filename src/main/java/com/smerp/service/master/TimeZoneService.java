package com.smerp.service.master;

import java.util.List;


import com.smerp.model.master.TimeZone;

public interface TimeZoneService {

	TimeZone save(TimeZone timeZone);
	
	List<TimeZone> findAll();
	
	TimeZone findById(int id);
	
	void delete(int id);
	
	TimeZone getInfo(int id);
	
	TimeZone findByName(String name);
}
