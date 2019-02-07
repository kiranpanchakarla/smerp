package com.smerp.repository.inventorytransactions;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smerp.model.inventorytransactions.InventoryGoodsReceiptList;

public interface InventoryGoodsReceiptListRepository extends JpaRepository<InventoryGoodsReceiptList,Integer> {

}
