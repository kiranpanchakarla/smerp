package com.smerp.service.master;

import java.util.List;
import com.smerp.model.master.Country;
import com.smerp.model.master.States;

public interface CountryServices {

	Country save(Country country);

	List<Country> countryList();

	Country findById(int countryId);

	void delete(int id);

	Country getInfo(int id);

	List<States> stateList(int countryId);
	
	Country findByName(String name);

}
