package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.Country;
import com.smerp.model.master.States;
import com.smerp.repository.master.CountryRepository;
import com.smerp.repository.master.CurrencyRepository;
import com.smerp.repository.master.StateRepository;
import com.smerp.service.master.CountryServices;

@Service
public class CountryServicesImpl implements CountryServices {

	private static final Logger logger = LogManager.getLogger(CountryServicesImpl.class);

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CurrencyRepository currencyRepository;

	public List<Country> countryList() {
		logger.info("inside CountryServicesImpl Country List method");
		return countryRepository.findAll();
	}

	public Country findById(int countryId) {
		logger.info("inside CountryServicesImpl findById method");
		return countryRepository.findById(countryId);
	}

	public List<States> stateList(int countryId) {
		logger.info("inside CountryServicesImpl State List method");
		return stateRepository.findBycountryId(countryId);
	}

	@Override
	public Country save(Country country) {
		logger.info("inside CountryServicesImpl save method");
		
		return countryRepository.save(country);
	}

	@Override
	public void delete(int id) {
		logger.info("inside CountryServicesImpl delete method");
		countryRepository.deleteById(id);

	}

	@Override
	public Country getInfo(int id) {
		logger.info("inside CountryServicesImpl getInfo method");
		return countryRepository.findById(id);
	}

	@Override
	public Country findByName(String name) {
		 
		return countryRepository.findByName(name);
	}

}
