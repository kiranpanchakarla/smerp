package com.smerp.service.inventory;

import java.util.List;

import org.springframework.data.repository.query.Param;
import com.smerp.model.admin.ProductQuantity;

public interface ProductQuantityService {

	 List<ProductQuantity> findProductOrderedQuantity(@Param("name")String name);
	 
	 ProductQuantity findProductReceivedQuantity(@Param("name")String name);
}
