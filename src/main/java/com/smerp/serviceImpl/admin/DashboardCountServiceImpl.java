package com.smerp.serviceImpl.admin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.DashboardCount;
import com.smerp.model.inventory.MinimumQuantityList;
import com.smerp.service.admin.DashboardCountService;

@Repository
@Transactional(value = TxType.REQUIRED)
public class DashboardCountServiceImpl implements DashboardCountService {

	private static final Logger logger = LogManager.getLogger(DashboardCountServiceImpl.class);
	
	@PersistenceContext    
	private EntityManager entityManager;
			
	@Override
	public DashboardCount findAll() {
		 
		String sql= "with countWise as ( select count(*) as totalCnt1 FROM tbl_purchase_purchase_req where  is_active=true) ," +
			    " openCnt as ( select count(*) as totalCnt2 from tbl_purchase_purchase_req where status='Open' and is_active=true) ," +
			    " cancelledCnt as ( select count(*) as totalCnt3 from tbl_purchase_purchase_req where status='Cancelled' and is_active=true ) ," +
			    " draftCnt as ( select count(*) as totalCnt4 from tbl_purchase_purchase_req where status='Draft' and is_active=true) ," +
			    " approvedCnt as ( select count(*) as totalCnt5 from tbl_purchase_purchase_req where status='Approved' and is_active=true) ," +
			    " convertedToRFQCnt as ( select count(*) as totalCnt6 from tbl_purchase_purchase_req where status='ConvertedToRFQ' and is_active=true)," +
			    " completed as ( select count(*) as totalCnt7 from tbl_purchase_purchase_req where status='Completed' and is_active=true)," +
			    " rejected as ( select count(*) as totalCnt8 from tbl_purchase_purchase_req where status='Rejected' and is_active=true)" +
			    " select countWise.totalCnt1 ,openCnt.totalCnt2,cancelledCnt.totalCnt3,draftCnt.totalCnt4,approvedCnt.totalCnt5,convertedToRFQCnt.totalCnt6,completed.totalCnt7,rejected.totalCnt8" +
			    " from countWise,openCnt,cancelledCnt,draftCnt,approvedCnt,convertedToRFQCnt,completed,rejected ";
		logger.info("Sql ----> " + sql);
		Query query = entityManager.createNativeQuery(sql);
		
		logger.info("sql ----> " + sql);
		  List<Object[]>	list = query.getResultList();
		
		logger.info("List Size -----> " + list.size());
		 DashboardCount dashboardCount = new DashboardCount();
	     for(Object[] tuple : list) {
	    	
	    	 dashboardCount.setTotal(tuple[0] == null ? 0 : ((BigInteger) tuple[0]).intValue());
	    	 dashboardCount.setOpen(tuple[1] == null ? 0 : ((BigInteger) tuple[1]).intValue());
	    	 dashboardCount.setCancelled(tuple[2] == null ? 0 : ((BigInteger) tuple[2]).intValue());
	    	 dashboardCount.setDraft(tuple[3] == null ? 0 : ((BigInteger) tuple[3]).intValue());
	    	 dashboardCount.setApproved(tuple[4] == null ? 0 : ((BigInteger) tuple[4]).intValue());
	    	 dashboardCount.setConvertedToRFQ(tuple[5] == null ? 0 : ((BigInteger) tuple[5]).intValue());
	    	 dashboardCount.setCompleted(tuple[6] == null ? 0 :((BigInteger) tuple[6]).intValue());
	    	 dashboardCount.setRejected(tuple[7] == null ? 0 : ((BigInteger) tuple[7]).intValue());
	    	
	     }
	     
	     logger.info("PR Count--------->"+dashboardCount); 
		return dashboardCount;
	}

	@Override
	public DashboardCount findRFQCount() {
		String rfqSQL= "with countWise as ( select count(*) as totalCnt1 from tbl_admin_rfq where  is_active=true) ,\r\n" + 
				"    openCnt as ( select count(*) as totalCnt2 from tbl_admin_rfq where status='Open' and is_active=true) ,\r\n" + 
				"    cancelledCnt as ( select count(*) as totalCnt3 from tbl_admin_rfq where status='Cancelled' and is_active=true ) ,\r\n" + 
				"    draftCnt as ( select count(*) as totalCnt4 from tbl_admin_rfq where status='Draft' and is_active=true) ,\r\n" + 
				"    approvedCnt as ( select count(*) as totalCnt5 from tbl_admin_rfq where status='Approved' and is_active=true) ,\r\n" + 
				"    convertedToRFQCnt as ( select count(*) as totalCnt6 from tbl_admin_rfq where status='ConvertedToRFQ' and is_active=true),\r\n" + 
				"    completed as ( select count(*) as totalCnt7 from tbl_admin_rfq where status='Completed' and is_active=true),\r\n" + 
				"    rejected as ( select count(*) as totalCnt8 from tbl_admin_rfq where status='Rejected' and is_active=true),\r\n" + 
				"    convertedToPO as ( select count(*) as totalCnt9 from tbl_admin_rfq where status='ConvertedToPO' and is_active=true)\r\n" + 
				"select countWise.totalCnt1 ,openCnt.totalCnt2,cancelledCnt.totalCnt3,draftCnt.totalCnt4,approvedCnt.totalCnt5,convertedToRFQCnt.totalCnt6,completed.totalCnt7,rejected.totalCnt8,convertedToPO.totalCnt9\r\n" + 
				"from countWise,openCnt,cancelledCnt,draftCnt,approvedCnt,convertedToRFQCnt,completed,rejected,convertedToPO ";
		
		Query query = entityManager.createNativeQuery(rfqSQL);
		logger.info("Query ----> " + query);
		logger.info("sql ----> " + rfqSQL);
		  List<Object[]>	list = query.getResultList();
		
		logger.info("List Size -----> " + list.size());
		 DashboardCount rfqCount = new DashboardCount();
	     for(Object[] tuple : list) {
	    	
	    	 rfqCount.setTotal(tuple[0] == null ? 0 : ((BigInteger) tuple[0]).intValue());
	    	 rfqCount.setOpen(tuple[1] == null ? 0 : ((BigInteger) tuple[1]).intValue());
	    	 rfqCount.setCancelled(tuple[2] == null ? 0 : ((BigInteger) tuple[2]).intValue());
	    	 rfqCount.setDraft(tuple[3] == null ? 0 : ((BigInteger) tuple[3]).intValue());
	    	 rfqCount.setApproved(tuple[4] == null ? 0 : ((BigInteger) tuple[4]).intValue());
	    	 rfqCount.setConvertedToRFQ(tuple[5] == null ? 0 : ((BigInteger) tuple[5]).intValue());
	    	 rfqCount.setCompleted(tuple[6] == null ? 0 :((BigInteger) tuple[6]).intValue());
	    	 rfqCount.setRejected(tuple[7] == null ? 0 : ((BigInteger) tuple[7]).intValue());
	    	 rfqCount.setConvertedToPO(tuple[8] == null ? 0 : ((BigInteger) tuple[8]).intValue());
	    	
	     }
	     
	     logger.info("RFQ Count--------->"+rfqCount); 
		return rfqCount;

	}

	@Override
	public DashboardCount findPOCount() {
		String poSQL= "with countWise as ( select count(*) as totalCnt1 from tbl_purchase_order where  is_active=true) ,\r\n" + 
				"    openCnt as ( select count(*) as totalCnt2 from tbl_purchase_order where status='Open' and is_active=true) ,\r\n" + 
				"    cancelledCnt as ( select count(*) as totalCnt3 from tbl_purchase_order where status='Cancelled' and is_active=true ) ,\r\n" + 
				"    draftCnt as ( select count(*) as totalCnt4 from tbl_purchase_order where status='Draft' and is_active=true) ,\r\n" + 
				"    approvedCnt as ( select count(*) as totalCnt5 from tbl_purchase_order where status='Approved' and is_active=true) ,\r\n" + 
				"    convertedToRFQCnt as ( select count(*) as totalCnt6 from tbl_purchase_order where status='ConvertedToRFQ' and is_active=true),\r\n" + 
				"    completed as ( select count(*) as totalCnt7 from tbl_purchase_order where status='Completed' and is_active=true),\r\n" + 
				"    rejected as ( select count(*) as totalCnt8 from tbl_purchase_order where status='Rejected' and is_active=true),\r\n" + 
				"    partiallyreceived as ( select count(*) as totalCnt9 from tbl_purchase_order where status='Partially_Received' and is_active=true),\r\n" + 
				"    convertedtopo as ( select count(*) as totalCnt10 from tbl_purchase_order where status='ConvertedToPO' and is_active=true),\r\n" + 
				"     closed as ( select count(*) as totalCnt11 from tbl_purchase_order where status='Closed' and is_active=true)   \r\n" + 
				"select countWise.totalCnt1 ,openCnt.totalCnt2,cancelledCnt.totalCnt3,draftCnt.totalCnt4,approvedCnt.totalCnt5,convertedToRFQCnt.totalCnt6,completed.totalCnt7,rejected.totalCnt8,\r\n" + 
				"partiallyreceived.totalCnt9,convertedtopo.totalCnt10,closed.totalCnt11 from countWise,openCnt,cancelledCnt,draftCnt,approvedCnt,convertedToRFQCnt,completed,rejected,partiallyreceived,convertedtopo,closed";
		
		Query query = entityManager.createNativeQuery(poSQL);
		logger.info("Query ----> " + query);
		logger.info("sql ----> " + poSQL);
		  List<Object[]>	list = query.getResultList();
		
		logger.info("List Size -----> " + list.size());
		 DashboardCount poCount = new DashboardCount();
	     for(Object[] tuple : list) {
	    	
	    	 poCount.setTotal(tuple[0] == null ? 0 : ((BigInteger) tuple[0]).intValue());
	    	 poCount.setOpen(tuple[1] == null ? 0 : ((BigInteger) tuple[1]).intValue());
	    	 poCount.setCancelled(tuple[2] == null ? 0 : ((BigInteger) tuple[2]).intValue());
	    	 poCount.setDraft(tuple[3] == null ? 0 : ((BigInteger) tuple[3]).intValue());
	    	 poCount.setApproved(tuple[4] == null ? 0 : ((BigInteger) tuple[4]).intValue());
	    	 poCount.setConvertedToRFQ(tuple[5] == null ? 0 : ((BigInteger) tuple[5]).intValue());
	    	 poCount.setCompleted(tuple[6] == null ? 0 :((BigInteger) tuple[6]).intValue());
	    	 poCount.setRejected(tuple[7] == null ? 0 : ((BigInteger) tuple[7]).intValue());
	    	 poCount.setPartiallyReceived(tuple[8] == null ? 0 :((BigInteger) tuple[8]).intValue());
	    	 poCount.setConvertedToPO(tuple[9] == null ? 0 : ((BigInteger) tuple[9]).intValue());
	    	 poCount.setClosed(tuple[10] == null ? 0 :((BigInteger) tuple[10]).intValue());
	     }
	     
	     logger.info("RFQ Count--------->"+poCount); 
		return poCount;
	}

	@Override
	public DashboardCount findGoodsReceiptCount() {
		String sql= "with countWise as ( select count(*) as totalCnt1 from tbl_goods_receipt where  is_active=true) ,\r\n" + 
				"    openCnt as ( select count(*) as totalCnt2 from tbl_goods_receipt where status='Open' and is_active=true) ,\r\n" + 
				"    cancelledCnt as ( select count(*) as totalCnt3 from tbl_goods_receipt where status='Cancelled' and is_active=true ) ,\r\n" + 
				"    draftCnt as ( select count(*) as totalCnt4 from tbl_goods_receipt where status='Draft' and is_active=true) ,\r\n" + 
				"    approvedCnt as ( select count(*) as totalCnt5 from tbl_goods_receipt where status='Approved' and is_active=true) ,\r\n" + 
				"    convertedToRFQCnt as ( select count(*) as totalCnt6 from tbl_goods_receipt where status='ConvertedToRFQ' and is_active=true),\r\n" + 
				"    completed as ( select count(*) as totalCnt7 from tbl_goods_receipt where status='Completed' and is_active=true),\r\n" + 
				"    rejected as ( select count(*) as totalCnt8 from tbl_goods_receipt where status='Rejected' and is_active=true),\r\n" + 
				"    convertedToPO as ( select count(*) as totalCnt9 from tbl_goods_receipt where status='ConvertedToPO' and is_active=true)\r\n" + 
				"select countWise.totalCnt1 ,openCnt.totalCnt2,cancelledCnt.totalCnt3,draftCnt.totalCnt4,approvedCnt.totalCnt5,completed.totalCnt7,rejected.totalCnt8\r\n" + 
				"from countWise,openCnt,cancelledCnt,draftCnt,approvedCnt,completed,rejected";
		
		Query query = entityManager.createNativeQuery(sql);
		logger.info("Query ----> " + query);
		logger.info("sql ----> " + sql);
		  List<Object[]>	list = query.getResultList();
		
		logger.info("List Size -----> " + list.size());
		 DashboardCount dashboardCount = new DashboardCount();
	     for(Object[] tuple : list) {
	    	
	    	 dashboardCount.setTotal(tuple[0] == null ? 0 : ((BigInteger) tuple[0]).intValue());
	    	 dashboardCount.setOpen(tuple[1] == null ? 0 : ((BigInteger) tuple[1]).intValue());
	    	 dashboardCount.setCancelled(tuple[2] == null ? 0 : ((BigInteger) tuple[2]).intValue());
	    	 dashboardCount.setDraft(tuple[3] == null ? 0 : ((BigInteger) tuple[3]).intValue());
	    	 dashboardCount.setApproved(tuple[4] == null ? 0 : ((BigInteger) tuple[4]).intValue());
	    	 dashboardCount.setCompleted(tuple[5] == null ? 0 : ((BigInteger) tuple[5]).intValue());
	    	 dashboardCount.setRejected(tuple[6] == null ? 0 :((BigInteger) tuple[6]).intValue());
	    	 
	     }
	     
	     logger.info("Goods Receipt Count--------->"+dashboardCount); 
		return dashboardCount;
	}

	@Override
	public List<MinimumQuantityList> minProductQtyList() {
		String qtysql= "with grWise as(select gol.product_number as grProduct,gol.warehouse as grWarehouse, sum(gol.required_quantity) as receivedQuantity\r\n" + 
				"from tbl_goods_receipt_lineitems as gol join  tbl_goods_receipt as gr  on gr.id =gol.gr_id  where  gol.product_number!='' and  gr.status in \r\n" + 
				" ('Approved','Goods_Return')\r\n" + 
				"group by gol.product_number,gol.warehouse),\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"productWise as (select products.product_no as productNumber,products.minimum as minQty,products.description as productName from tbl_inventory_product as products\r\n" + 
				" where products.is_delete = true),\r\n" + 
				"\r\n" + 
				"warehouseWise as (select plant.plant_name as warehouse, plant.plant_id as warehouseId from tbl_admin_plant as plant),\r\n" + 
				"\r\n" + 
				"qtyWise as (Select productWise.productNumber as productNo,productWise.productName as productName,COALESCE(warehouseWise.warehouse,'N/A') as warehouse,\r\n" + 
				"COALESCE(productWise.minQty,0)as minQty,COALESCE(grWise.receivedQuantity,0)as InStock from productWise \r\n" + 
				"left outer join grWise on grWise.grProduct = productWise.productNumber \r\n" + 
				"left outer join warehouseWise on grWise.grWarehouse = warehouseWise.warehouseId)\r\n" + 
				"\r\n" + 
				"select qtyWise.productNo,qtyWise.productName,qtyWise.warehouse,qtyWise.minQty, qtyWise.inStock from qtyWise \r\n" + 
				"where qtyWise.inStock < qtyWise.minQty order by qtyWise.productNo";
		
		Query query1 = entityManager.createNativeQuery(qtysql);
		 
		logger.info("Product ordered ----> " + query1);
		logger.info("Product ordered SQL ----> " + qtysql);
		
		//List<Object[]>	list1 = query1.getResultList();
		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());
		
		List<MinimumQuantityList> productList = new ArrayList<>();
		
		 for(Object[] tuple : arrayList) {
			 MinimumQuantityList prolist = new MinimumQuantityList();
			 
			 prolist.setProductNumber(tuple[0].toString());
			 prolist.setProductName(tuple[1].toString());
			 prolist.setWarehouse(tuple[2].toString());
			 prolist.setMinQty(tuple[3] == null ? 0 : ((Integer) tuple[3]).intValue());
			 prolist.setInStock(tuple[4] == null ? 0 : ((BigInteger) tuple[4]).intValue());
			 productList.add(prolist);
		 }
		
		return productList;
	}

}
