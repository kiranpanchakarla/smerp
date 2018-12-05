package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.smerp.model.inventory.UomCategory;

public interface UomCategoryRepository extends JpaRepository<UomCategory, Integer> {

	UomCategory findById(int id);
	
	@Query("SELECT c FROM UomCategory c WHERE LOWER(c.uomCategoryName) = LOWER(:name)")
	UomCategory findByName(@Param("name") String name);
	
}
