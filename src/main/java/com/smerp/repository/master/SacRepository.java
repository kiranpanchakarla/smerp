package com.smerp.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smerp.model.master.SACCode;

@Repository
public interface SacRepository extends JpaRepository<SACCode, Integer> {

	SACCode findById(int id);
	@Query("SELECT s FROM SACCode s WHERE (s.sacCode) = (:sacCode)")
    SACCode findBySacCode(@Param("sacCode") String sacCode);
	
	@Query("SELECT sacCode FROM SACCode")
    List<String> findAllSacCodes();
	
	@Query("SELECT c FROM SACCode c WHERE LOWER(c.sacCode) = LOWER(:name)")
	SACCode findByCode(@Param("name") String name);
}
