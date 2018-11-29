package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.master.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	Country findById(int id);
	
	@Query("SELECT c FROM Country c WHERE LOWER(c.name) = LOWER(:name)")
	Country findByName(@Param("name") String name);
	
}
