package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer> {
    String plantN= "1";
	@Query("SELECT P FROM PurchaseRequest P WHERE isActive=:isActive and plant.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt desc" )
	List<PurchaseRequest> findByIsActive(@Param("isActive")Boolean isActive, @Param("plantIds") int[] plantIds,@Param("secondApp") Boolean [] secondApp);
	
	@Query("SELECT P FROM PurchaseRequest P WHERE status=:status and plant.id in (:plantIds) and secondLevelEnable in (:secondApp) order by createdAt desc")
	List<PurchaseRequest> prApprovedList(@Param("status") String status, @Param("plantIds") int[] plantIds, @Param("secondApp") Boolean [] secondApp);

	PurchaseRequest findTopByOrderByIdDesc();
	
	List<PurchaseRequest> findByDocNumber(String docNumber);
	
	@Query("SELECT l from PurchaseRequestList l where l.purchaseRequestId=:pId and l.activeStatus=:activeStatus")
    List<PurchaseRequestList> getPurchaseRequestListById(@Param("pId") PurchaseRequest pId,@Param("activeStatus") String activeStatus);
	
}
