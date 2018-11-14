package com.smerp.service.master;

import java.util.List;

import com.smerp.model.master.States;

public interface StatesService {

	States save(States states);
	
	List<States> findAll();
	
	States findById(int id);
	
	void delete(int id);
	
	States getInfo(int id);
}
