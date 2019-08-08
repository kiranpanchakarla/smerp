package com.smerp.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.master.States;

public interface StateRepository extends JpaRepository<States, Integer> {
	List<States> findBycountryId(int contryId);

	States findById(int id);
	
	@Query("SELECT c FROM States c WHERE LOWER(c.name) = LOWER(:name)")
	States findByName(@Param("name") String name);
	
	@Query("SELECT c FROM States c WHERE LOWER(c.code) = LOWER(:name)")
	States findByCode(@Param("name") String name);
}
