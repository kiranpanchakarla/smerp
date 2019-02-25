package com.smerp.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.CreditMemoLineItems;

public interface CreditMemoLineItemsRepository extends JpaRepository<CreditMemoLineItems, Integer> {

}
