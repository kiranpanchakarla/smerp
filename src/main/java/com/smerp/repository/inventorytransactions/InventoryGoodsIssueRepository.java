package com.smerp.repository.inventorytransactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;

public interface InventoryGoodsIssueRepository extends JpaRepository<InventoryGoodsIssue,Integer> {

	@Query("SELECT r FROM InventoryGoodsIssue r WHERE isActive=:isActive order by createdAt asc")
	List<InventoryGoodsIssue> findByIsActive(Boolean isActive);
	
	InventoryGoodsIssue findTopByOrderByIdDesc();
}
