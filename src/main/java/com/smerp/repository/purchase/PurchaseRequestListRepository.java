package com.smerp.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.smerp.model.purchase.PurchaseRequestList;

@Transactional(readOnly = true)
public interface PurchaseRequestListRepository extends JpaRepository<PurchaseRequestList, Integer> {

	
    @Modifying
    @Transactional
    @Query("delete from PurchaseRequestList p where p.id=:id")
    void deleteByPeqId(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE PurchaseRequestList pl SET pl.activeStatus = :status WHERE pl.id=:ids")
	int updatePurchaseRequestList(@Param("ids") Integer ids,@Param("status") String status);

}
