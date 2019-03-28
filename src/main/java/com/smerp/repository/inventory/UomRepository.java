package com.smerp.repository.inventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.Uom;
import com.smerp.model.inventory.UomCategory;

public interface UomRepository extends JpaRepository<Uom, Integer> {
	List<Uom> findByuomCategoryId(int uomCategoryId);

	Uom findById(int id);
	
	@Query("SELECT c FROM Uom c WHERE LOWER(c.uomName) = LOWER(:name)")
	Uom findByName(@Param("name") String name);
	
	@Query("SELECT c.uomName FROM Uom c")
	List<Uom> getUOM();
 
}
