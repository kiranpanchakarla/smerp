package com.smerp.service.master;

import java.util.List;
import com.smerp.model.master.HSNCode;

public interface HsnService {

	HSNCode save(HSNCode hsncode);

	List<HSNCode> findAll();

	HSNCode findById(int id);

	void delete(int id);
	
	HSNCode getInfo(int id);
}
