package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Plant;
import com.smerp.model.master.Currency;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

	Plant findById(int id);
	
	@Query("SELECT p FROM Plant p WHERE id = :id")
	List<Plant> findOneList(@Param("id") int id);
	
	@Query("SELECT c FROM Plant c WHERE LOWER(c.plantName) = LOWER(:name)")
	Plant findByName(@Param("name") String name);
}
