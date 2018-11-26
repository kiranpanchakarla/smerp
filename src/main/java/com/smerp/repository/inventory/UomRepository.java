package com.smerp.repository.inventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smerp.model.inventory.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {
	List<Uom> findByuomCategoryId(int uomCategoryId);

	Uom findById(int id);

}
