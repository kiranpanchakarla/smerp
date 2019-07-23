package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.master.HSNCode;

@Repository
public interface HsnRepository extends JpaRepository<HSNCode, Integer> {

	HSNCode findById(int id);
	
	@Query("SELECT c FROM HSNCode c WHERE LOWER(c.hsnCode) = LOWER(:name)")
	HSNCode findByCode(@Param("name") String name);
}
