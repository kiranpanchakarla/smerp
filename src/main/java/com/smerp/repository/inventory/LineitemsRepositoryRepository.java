package com.smerp.repository.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.smerp.model.inventory.LineItems;
import com.smerp.model.inventory.RequestForQuotation;

public interface LineitemsRepositoryRepository extends JpaRepository<LineItems, Integer> {

	/* @Transactional
	 @Modifying
	 @Query("delete from LineItems l where l.requestForQuotation.id =:#{#requestForQuotation.id}")
    List<LineItems>  deleteByrfqId(@Param("requestForQuotation") RequestForQuotation requestForQuotation);*/
}
