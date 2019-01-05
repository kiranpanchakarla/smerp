package com.smerp.repository.purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.GoodsReceiptLineItems;

public interface GoodsReceiptLineItemsRepository extends JpaRepository<GoodsReceiptLineItems, Integer> {

}
