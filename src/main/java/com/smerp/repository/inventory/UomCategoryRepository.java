package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.UomCategory;

public interface UomCategoryRepository extends JpaRepository<UomCategory, Integer> {

}
