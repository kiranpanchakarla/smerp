package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.InVoice;

public interface CreditMemoRepository  extends JpaRepository<CreditMemo, Integer> {
	@Query("SELECT r FROM CreditMemo r WHERE isActive=:isActive and plant.id in (:plantIds)  order by createdAt desc")
	List<CreditMemo> findByIsActive(Boolean isActive, int[] plantIds);
	
	CreditMemo findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM CreditMemo r WHERE invId=:inv and status!=:status")
	List<CreditMemo> findByListInvId(InVoice inv,String status);
	
	@Query("SELECT r FROM CreditMemo r WHERE invId=:inv and status = :status order by createdAt desc")
	List<CreditMemo> findByApproveListInvId(InVoice inv ,String status);
	
	CreditMemo findByinvId(InVoice inv);
}
