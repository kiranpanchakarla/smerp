package com.smerp.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.GoodsReturnLineItems;

public interface GoodsReturnLineItemsRepository extends JpaRepository<GoodsReturnLineItems, Integer> {

}

