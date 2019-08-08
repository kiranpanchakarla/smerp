package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.InVoice;

public interface InVoiceRepository  extends JpaRepository<InVoice, Integer> {
	
	@Query("SELECT r FROM InVoice r WHERE isActive=:isActive and plant.id in (:plantIds)  order by createdAt desc")
	List<InVoice> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds);
	
	InVoice findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM InVoice r WHERE grId=:gr and status!=:status")
	List<InVoice> findByListGrId(@Param("gr") GoodsReceipt gr,@Param("status") String status);
	
	@Query("SELECT r FROM InVoice r WHERE grId=:gr and status = :status order by createdAt desc")
	List<InVoice> findByApproveListGrId(@Param("gr") GoodsReceipt gr ,@Param("status") String status);
	
	
	@Query("SELECT r FROM InVoice r WHERE status = :status and plant.id in (:plantIds) order by createdAt desc")
	List<InVoice> invApprovedList(@Param("status") String status ,@Param("plantIds") int[] plantIds);
	
	InVoice findByGrId(GoodsReceipt gr);
	
	List<InVoice> findByDocNumber(String invDocNum);
}
