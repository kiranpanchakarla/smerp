package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.InVoice;

public interface InVoiceRepository  extends JpaRepository<InVoice, Integer> {
	
	@Query("SELECT r FROM InVoice r WHERE isActive=:isActive and plant.id in (:plantIds)  order by createdAt desc")
	List<InVoice> findByIsActive(Boolean isActive, int[] plantIds);
	
	InVoice findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM InVoice r WHERE grId=:gr and status!=:status")
	List<InVoice> findByListGrId(GoodsReceipt gr,String status);
	
	@Query("SELECT r FROM InVoice r WHERE grId=:gr and status = :status order by createdAt desc")
	List<InVoice> findByApproveListGrId(GoodsReceipt gr ,String status);
	
	
	@Query("SELECT r FROM InVoice r WHERE status = :status order by createdAt desc")
	List<InVoice> invApprovedList(String status);
	
	InVoice findByGrId(GoodsReceipt gr);
	
	List<InVoice> findByDocNumber(String invDocNum);
}
