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
	public List<ProductQuantity> findProductOrderedQuantity(@Param("name")String name) {
		
		String posql= " with prWise as (select polist.product_number as prProduct ,polist.warehouse as prwarehouse,sum(polist.required_quantity) as productQuantity\r\n" + 
				"from tbl_purchase_order_lineitems as polist join  tbl_purchase_order as po  on po.id =polist.po_id  \r\n" + 
				"and polist.product_number =" + "'" + name + "'" + " and  po.status in ('Approved','Partially_Received','Completed')  \r\n" + 
				"group by polist.product_number,polist.warehouse order by polist.warehouse),\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"grWise as(select grlist.product_number as grproduct,       \r\n" + 
				"grlist.warehouse as grwarehouse,sum(grlist.required_quantity)\r\n" + 
				" as receivedQuantity from tbl_goods_receipt_lineitems as grlist \r\n" + 
				" join tbl_goods_receipt as gr on gr.id = grlist.gr_id and grlist.product_number ="+"'"+name +"'" + " and gr.status in \r\n" + 
				" ('Approved','Goods_Return')  and gr.reference_doc_number != '' \r\n" + 
				"  group by grlist.product_number,grlist.warehouse order by grlist.product_number), "
				+ "directgrWise as(select grlist.product_number as grproduct,       \r\n" + 
				"grlist.warehouse as grwarehouse,sum(grlist.required_quantity)\r\n" + 
				" as directQuantity from tbl_goods_receipt_lineitems as grlist \r\n" + 
				" join tbl_goods_receipt as gr on gr.id = grlist.gr_id and grlist.product_number =" + "'" + name+ "'" + " and gr.status in \r\n" + 
				" ('Approved','Goods_Return') and gr.reference_doc_number = '' \r\n" + 
				"  group by grlist.product_number,grlist.warehouse order by grlist.product_number),\r\n" + 
				"\r\n" + 
				" warehouseWise as (select plant.plant_name as warehouse, plant.plant_id as warehouseId from tbl_admin_plant as plant)\r\n" + 
				"\r\n" + 
				"select warehouseWise.warehouseId,warehouseWise.warehouse\r\n" + 
				",COALESCE(prWise.productQuantity,0) as po,\r\n" + 
				"COALESCE(grWise.receivedQuantity,0)as gr,\r\n" + 
				"COALESCE(directgrWise.directQuantity,0)as directgr\r\n" + 
				" from\r\n" + 
				"warehouseWise \r\n" + 
				"left join directgrWise on  directgrWise.grwarehouse = warehouseWise.warehouseId\r\n" + 
				"left join grWise on  grWise.grwarehouse = warehouseWise.warehouseId\r\n" + 
				"left join prWise on   prWise.prwarehouse= warehouseWise.warehouseId\r\n" + 
				"\r\n" + 
				"order by warehouseWise.warehouseId";
		
		
		 
		
		
		Query query1 = entityManager.createNativeQuery(posql);
		 
		logger.info("Product ordered ----> " + query1);
		
		logger.info("Product ordered SQL ----> " + posql);
		
		 
		//List<Object[]>	list1 = query1.getResultList();
		ArrayList<Object[]> arrayList = new ArrayList<>();
		 
		arrayList.addAll(query1.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());
		 
		 List<ProductQuantity> productQuantities = new ArrayList<>();
		  
		 for(Object[] tuple : arrayList) {
			 ProductQuantity productQuantity = new ProductQuantity();
			  
			 productQuantity.setWarehouseCode(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 productQuantity.setWarehouseName((tuple[1]).toString());
			 productQuantity.setOrdered(tuple[2] == null ? 0 : ((BigInteger) tuple[2]).intValue());
			 productQuantity.setPoToGr(tuple[3] == null ? 0 : ((BigInteger) tuple[3]).intValue());
			 productQuantity.setDirectGr(tuple[4] == null ? 0 : ((BigInteger) tuple[4]).intValue());
			 productQuantities.add(productQuantity);
		 }
	
		 logger.info("PR Count--------->"+productQuantities); 
		 
		return productQuantities;
	}

	@Override
	public ProductQuantity findProductReceivedQuantity(String name) {
		return null;
	 
	}

}
