package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.PurchaseOrderActivityHistory;

public interface PurchaseOrderActivityHistoryRepository extends JpaRepository<PurchaseOrderActivityHistory,Integer> {

	@Query("SELECT r FROM PurchaseOrderActivityHistory r WHERE po_id=:id order by createdDate")
	List<PurchaseOrderActivityHistory> findByHistoryListByPOId(Integer id);
}
