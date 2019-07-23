package com.smerp.repository.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.inventory.LineItems;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;

public interface RequestForQuotationRepository extends JpaRepository<RequestForQuotation, Integer> {
	
	@Query("SELECT r FROM RequestForQuotation r WHERE isActive=:isActive and plant.id in (:plantIds)  order by createdAt desc")
	List<RequestForQuotation> findByIsActive(@Param("isActive") Boolean isActive,@Param("plantIds") int[] plantIds);
	
	@Query("SELECT r FROM RequestForQuotation r WHERE status=:status and plant.id in (:plantIds) order by createdAt desc")
	List<RequestForQuotation> rfqApprovedList(@Param("status") String status,@Param("plantIds") int[] plantIds);
	
	RequestForQuotation findTopByOrderByIdDesc();
	
	RequestForQuotation findByPurchaseReqId(PurchaseRequest purchaseReqId);
	
	List<RequestForQuotation> findByDocNumber(String rfqDocNum);
	
	@Query("SELECT r FROM RequestForQuotation r WHERE vendor.name=:vendorName and referenceDocNumber=:refDocNum")
	List<RequestForQuotation> findByDocNumber(@Param("vendorName") String vendorName,@Param("refDocNum") String refDocNum);
	
	@Query("SELECT r FROM RequestForQuotation r WHERE purchaseReqId=:prId")
	List<RequestForQuotation> getRFQListById(@Param("prId") PurchaseRequest prId);
	
	@Query("SELECT l FROM LineItems l, RequestForQuotation r WHERE l.id = r.id and r.purchaseReqId=:prId")
	List<LineItems> getRFQLineItemsListById(@Param("prId") PurchaseRequest prId);
}
