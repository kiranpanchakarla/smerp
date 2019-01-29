package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.InVoice;

public interface InVoiceRepository  extends JpaRepository<InVoice, Integer> {
	
@Query("SELECT r FROM InVoice r WHERE isActive=:isActive order by createdAt asc")
List<InVoice> findByIsActive(Boolean isActive);

InVoice findTopByOrderByIdDesc();

@Query("SELECT r FROM InVoice r WHERE grId=:id and status!=:status")
List<InVoice> findByListGrId(int id,String status);

@Query("SELECT r FROM InVoice r WHERE grId=:id and status = :status")
List<InVoice> findByApproveListGrId(int id ,String status);

InVoice findByGrId(int id);
}
