package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.smerp.model.master.TimeZone;

public interface TimeZoneRepository  extends JpaRepository<TimeZone,Integer> {

	TimeZone findById(int id);
	
	@Query("SELECT c FROM TimeZone c WHERE LOWER(c.name) = LOWER(:name)")
	TimeZone findByName(@Param("name") String name);
}
