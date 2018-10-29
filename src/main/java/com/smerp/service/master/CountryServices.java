package com.smerp.service.master;

import java.util.List;
import com.smerp.model.master.Country;
import com.smerp.model.master.States;

public interface CountryServices {
	List<Country> countryList();
	List<States> stateList(int countryId);
}
