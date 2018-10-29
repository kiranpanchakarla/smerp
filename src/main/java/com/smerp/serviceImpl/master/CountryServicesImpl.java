package com.smerp.serviceImpl.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.master.Country;
import com.smerp.model.master.States;
import com.smerp.repository.master.CountryRepository;
import com.smerp.repository.master.StateRepository;
import com.smerp.service.master.CountryServices;
@Service
public class CountryServicesImpl implements CountryServices{

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	
	public List<Country> countryList(){
		return countryRepository.findAll();
	}
	
	public List<States> stateList(int countryId){
		return stateRepository.findBycountryId(countryId);
	}
	
}
