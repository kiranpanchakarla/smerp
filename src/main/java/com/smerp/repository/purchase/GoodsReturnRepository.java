package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smerp.model.inventory.GoodsReturn;

public interface GoodsReturnRepository  extends JpaRepository<GoodsReturn, Integer> {
	
	@Query("SELECT r FROM GoodsReturn r WHERE isActive=:isActive order by createdAt desc")
	List<GoodsReturn> findByIsActive(Boolean isActive);
	
	GoodsReturn findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:id and status!=:status order by createdAt desc")
	List<GoodsReturn> findByListgrId(int id,String status);
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:id and status = :status order by createdAt desc")
	List<GoodsReturn> findByApproveListGrId(int id ,String status);
	
	GoodsReturn findBygrId(int id);

}
