package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

	Plant findById(int id);
	
	@Query("SELECT p FROM Plant p WHERE id = :id")
	List<Plant> findOneList(int id);
}
