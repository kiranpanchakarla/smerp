package com.smerp.service.master;

import java.util.List;
import com.smerp.model.master.SACCode;

public interface SacService {

    List<SACCode> findAll();
	
	SACCode save(SACCode savcode);
	
	SACCode findById(int id);
	
	void delete(int id);
    
	SACCode getInfo(int id);
	
	List<String> findAllSacCodes();
	
	SACCode findBySacCode(String sacCode);
}
