package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smerp.model.master.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	Country findById(int id);
	
}
