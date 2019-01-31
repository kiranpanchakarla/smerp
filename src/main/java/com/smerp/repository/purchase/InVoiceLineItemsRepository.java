package com.smerp.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.inventory.InVoiceLineItems;

public interface InVoiceLineItemsRepository extends JpaRepository<InVoiceLineItems, Integer> {

}
