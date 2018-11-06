package com.smerp.service.master;

import java.util.List;
import com.smerp.model.master.Languages;

public interface LanguagesService {

	Languages save(Languages languages);

	List<Languages> findAll();

	Languages findById(int id);

	void delete(int id);
	
	Languages getInfo(int id);
}
