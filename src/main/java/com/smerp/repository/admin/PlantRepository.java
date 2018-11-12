package com.smerp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

}
