package com.smerp.serviceImpl.inventory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.ProductQuantity;
import com.smerp.service.inventory.ProductQuantityService;
@Service
public class ProductQuantityServiceImpl implements ProductQuantityService {

	private static final Logger logger = LogManager.getLogger(ProductQuantityServiceImpl.class);
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductQuantity> findProductOrderedQuantity(@Param("id")Integer id) {
		
		String productsql= " select * from vw_inventory_product_quantity where product_id = '" + id + " ' ";
		
		
		 
		
		
		Query query1 = entityManager.createNativeQuery(productsql);
		 
		logger.info("Product ordered ----> " + query1);
		
		logger.info("Product ordered SQL ----> " + productsql);
		
		 
		//List<Object[]>	list1 = query1.getResultList();
		ArrayList<Object[]> arrayList = new ArrayList<>();
		 
		arrayList.addAll(query1.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());
		 
		 List<ProductQuantity> productQuantities = new ArrayList<>();
		  
		 for(Object[] tuple : arrayList) {
			 ProductQuantity productQuantity = new ProductQuantity();
			  
			 productQuantity.setProductId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 productQuantity.setWarehouse((tuple[3]).toString());
			 productQuantity.setInStock((Integer)(tuple[4] == null ? 0 : (Integer.parseInt((tuple[4].toString())))));
			 productQuantity.setOrdered((Integer)(tuple[5] == null ? 0 : (Integer.parseInt((tuple[5].toString())))));
			 productQuantity.setAvaliableQuantity((Integer)(tuple[6] == null ? 0 : (Integer.parseInt((tuple[6].toString())))));
			 productQuantities.add(productQuantity);
		 }
	 
		 logger.info("PR Count--------->"+productQuantities); 
		 
		return productQuantities;
	}

	 

}
