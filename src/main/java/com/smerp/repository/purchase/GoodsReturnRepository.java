package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;

public interface GoodsReturnRepository  extends JpaRepository<GoodsReturn, Integer> {
	
	@Query("SELECT r FROM GoodsReturn r WHERE isActive=:isActive and plant.id in (:plantIds)  order by createdAt desc")
	List<GoodsReturn> findByIsActive(Boolean isActive, int[] plantIds);
	
	GoodsReturn findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:gr and status!=:status order by createdAt desc")
	List<GoodsReturn> findByListgrId(GoodsReceipt gr,String status);
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:gr and status = :status order by createdAt desc")
	List<GoodsReturn> findByApproveListGrId(GoodsReceipt gr ,String status);
	
	GoodsReturn findBygrId(GoodsReturn gr);

}
