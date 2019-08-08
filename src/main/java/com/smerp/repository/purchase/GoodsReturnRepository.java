package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;

public interface GoodsReturnRepository  extends JpaRepository<GoodsReturn, Integer> {
	
	@Query("SELECT r FROM GoodsReturn r WHERE isActive=:isActive and plant.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt desc")
	List<GoodsReturn> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds,@Param("secondApp") Boolean [] secondApp);
	
	GoodsReturn findTopByOrderByIdDesc();
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:gr and status!=:status order by createdAt desc")
	List<GoodsReturn> findByListgrId(@Param("gr") GoodsReceipt gr,@Param("status") String status);
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:gr and status = :status and plant.id in (:plantIds) order by createdAt desc")
	List<GoodsReturn> findByApproveListGrId(@Param("gr") GoodsReceipt gr, @Param("status") String status,@Param("plantIds") int[] plantIds);
	
	GoodsReturn findBygrId(GoodsReturn gr);
	
	@Query("SELECT r FROM GoodsReturn r WHERE grId=:gr and status=:status order by createdAt desc")
	List<GoodsReturn> getListBygrId(@Param("gr") GoodsReceipt gr, @Param("status") String status);

}
