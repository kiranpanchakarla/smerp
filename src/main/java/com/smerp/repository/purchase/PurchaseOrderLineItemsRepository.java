package com.smerp.repository.purchase;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.PurchaseOrderLineItems;

public interface PurchaseOrderLineItemsRepository extends JpaRepository<PurchaseOrderLineItems, Integer> {

}
