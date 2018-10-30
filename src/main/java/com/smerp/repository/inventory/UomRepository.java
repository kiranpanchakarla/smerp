package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {

}
