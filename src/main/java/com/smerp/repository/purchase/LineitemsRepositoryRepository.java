package com.smerp.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.smerp.model.inventory.LineItems;
@Transactional(readOnly = true)
public interface LineitemsRepositoryRepository extends JpaRepository<LineItems, Integer> {

	    @Modifying
	    @Transactional
	    @Query("delete from LineItems l where l.id=:id")
	    void deleteByLineId(@Param("id")Integer id);
	
}

