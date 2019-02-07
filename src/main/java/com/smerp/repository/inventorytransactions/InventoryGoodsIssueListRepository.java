package com.smerp.repository.inventorytransactions;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventorytransactions.InventoryGoodsIssueList;

public interface InventoryGoodsIssueListRepository extends JpaRepository<InventoryGoodsIssueList,Integer> {

}
