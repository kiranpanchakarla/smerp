package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.CreditMemo;

public interface CreditMemoRepository  extends JpaRepository<CreditMemo, Integer> {
	@Query("SELECT r FROM CreditMemo r WHERE isActive=:isActive order by createdAt desc")
	List<CreditMemo> findByIsActive(Boolean isActive);
	
	CreditMemo findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM CreditMemo r WHERE invId=:id and status!=:status")
	List<CreditMemo> findByListInvId(int id,String status);
	
	@Query("SELECT r FROM CreditMemo r WHERE invId=:id and status = :status order by createdAt desc")
	List<CreditMemo> findByApproveListInvId(int id ,String status);
	
	CreditMemo findByinvId(int id);
}
