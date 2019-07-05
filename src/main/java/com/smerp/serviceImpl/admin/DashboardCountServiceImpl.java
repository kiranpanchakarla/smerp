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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.DashboardCount;
import com.smerp.model.inventory.InventoryGoodsIssueList;
import com.smerp.model.inventory.InventoryProductsList;
import com.smerp.model.inventory.MinimumQuantityList;
import com.smerp.service.admin.DashboardCountService;
import com.smerp.service.master.PlantService;

@Repository
@Transactional(value = TxType.REQUIRED)
public class DashboardCountServiceImpl implements DashboardCountService {

	private static final Logger logger = LogManager.getLogger(DashboardCountServiceImpl.class);
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	@Autowired
	PlantService plantService;

	
	/*@Override
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
*/
	@Override
	public List<MinimumQuantityList> minProductQtyList(int id) {
		String qtysql= "select pq.*,p.minimum,p.description as minqty From vw_inventory_product_quantity pq \r\n" + 
				"inner join tbl_inventory_product p on p.product_id=pq.product_id and pq.instock_quantity<p.minimum\r\n" + 
				"where plant_id = ' "+ id + "';";
		
		Query query1 = entityManager.createNativeQuery(qtysql);
		 
		logger.info("method ---> minProductQtyList");
		
		//List<Object[]>	list1 = query1.getResultList();
		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());
		
		List<MinimumQuantityList> productList = new ArrayList<>();
		
		 for(Object[] tuple : arrayList) {
			 MinimumQuantityList prolist = new MinimumQuantityList();
			 
			 
			 prolist.setProductNumber(tuple[1].toString());
			 prolist.setWarehouse(tuple[3].toString());
			 prolist.setInStock(tuple[4] == null ? 0: (Double.parseDouble(tuple[4].toString())));
			 prolist.setMinQty(tuple[7] == null ? 0 : ((Integer) tuple[7]).intValue());
			 prolist.setProductName(tuple[8].toString());
			 productList.add(prolist);
		 }
		
		return productList;
	}

 

	@Override
	public List<InventoryProductsList> inventoryQtyList(int id) {
		String qtysql= "select * from vw_inventory_status_report where plant_id=" + id ;
		Query query1 = entityManager.createNativeQuery(qtysql);
		 
		logger.info("method --->  inventoryQtyList");
		
		//List<Object[]>	list1 = query1.getResultList();
		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());
		
		List<InventoryProductsList> inventoryList = new ArrayList<>();
		 for(Object[] tuple : arrayList) {
			 InventoryProductsList prolist = new InventoryProductsList();
			 prolist.setProductNo(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setProductName(tuple[1].toString());
			 prolist.setPlantId(tuple[2] == null ? 0 : ((Integer) tuple[2]).intValue());
			 prolist.setPlantName(tuple[3].toString());
			 prolist.setProductDescription(tuple[4].toString());
			 prolist.setProductGroupDescription(tuple[5].toString());
			 prolist.setUomName(tuple[6].toString());
			 prolist.setInstockQty(tuple[7] == null ? 0: (Double.parseDouble(tuple[7].toString())));
			 inventoryList.add(prolist);
		 }
		 
		return inventoryList;
	}

	@Override
	public List<InventoryGoodsIssueList> inventoryGoodsIssueList(int id) {
		String qtysql= "select * from vw_inventory_goods_issue_daily where is_active = 't' and ware_house=" + id ;
		Query query1 = entityManager.createNativeQuery(qtysql);
		 
		
		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());
		
		List<InventoryGoodsIssueList> inventoryGIList = new ArrayList<>();
		 for(Object[] tuple : arrayList) {
			 InventoryGoodsIssueList prolist = new InventoryGoodsIssueList();
			 prolist.setDocId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setDocNumber(tuple[1].toString());
			 prolist.setDocDate(tuple[2].toString());
			 prolist.setProductId(tuple[3] == null ? 0 : ((Integer) tuple[3]).intValue());
			 prolist.setProductNumber(tuple[4].toString());
			 prolist.setProductDescription(tuple[5].toString());
			 prolist.setProductGroup(tuple[6].toString());
			 prolist.setUomName(tuple[7].toString());
			 prolist.setRequiredQty(tuple[8] == null ? 0: ((Integer) tuple[8]).intValue());
			 prolist.setDepartmentId(tuple[9] == null ? 0: ((Integer) tuple[9]).intValue());
			 prolist.setDepartmentName(tuple[10].toString());
			 prolist.setRemarks(tuple[11].toString());
			 prolist.setWarehouseId(tuple[12] == null ? 0: ((Integer) tuple[12]).intValue());
			 
			 inventoryGIList.add(prolist);
		 }
		 
		return inventoryGIList;
		 
	}

	@Override
	public List<DashboardCount> getCount() {
		//String sql= "select * from vw_dashboard";
		int[] plantIds = plantService.findPlantIds();
		String plantId="";
		for(int i : plantIds ) {
			plantId += "," +i;
		}
		plantId = plantId.substring(1,plantId.length());
		logger.info("method --->  getCount");
		String sql = "\r\n" + 
				"select status\r\n" + 
				",sum(pr_count) as prCount \r\n" + 
				",sum(rfq_count) as rfqCount \r\n" + 
				",sum(po_count) as poCount \r\n" + 
				",sum(gr_count) as grCount \r\n" + 
				",sum(gre_count) as greCount \r\n" + 
				",sum(inv_count) as invCount \r\n" + 
				",sum(cre_count) as creCount \r\n" + 
				",sum(invgr_count) as invgrCount \r\n" + 
				",sum(invgi_count) as invgiCount \r\n" + 
				",sum(invgt_count) as invgtCount \r\n" + 
				"from vw_dashboard_plant where plant_id in " + " ( " + plantId  + ") "
				+ " group by status order by status ";
		Query query = entityManager.createNativeQuery(sql);
		
		 
		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query.getResultList());
		
		
		
		List<DashboardCount> list = new ArrayList<>();
		  
		 for(Object[] tuple : arrayList) {
			 DashboardCount dashboardCount = new DashboardCount();
	    	 dashboardCount.setStatus(tuple[0].toString());
	    	 dashboardCount.setPrCount(tuple[1] == null ? 0 : (Integer.parseInt(tuple[1].toString())));
	    	 dashboardCount.setRfqCount(tuple[2] == null ? 0 : (Integer.parseInt(tuple[2].toString())));
	    	 dashboardCount.setPoCount(tuple[3] == null ? 0 : (Integer.parseInt(tuple[3].toString())));
	    	 dashboardCount.setGrCount(tuple[4] == null ? 0 : (Integer.parseInt(tuple[4].toString())));
	    	 dashboardCount.setGreCount(tuple[5] == null ? 0 : (Integer.parseInt(tuple[5].toString())));
	    	 dashboardCount.setInvCount(tuple[6] == null ? 0 :(Integer.parseInt(tuple[6].toString())));
	    	 dashboardCount.setCreCount(tuple[7] == null ? 0 : (Integer.parseInt(tuple[7].toString())));
	    	 dashboardCount.setInvgrCount(tuple[8] == null ? 0 : (Integer.parseInt(tuple[8].toString())));
	    	 dashboardCount.setInvgiCount(tuple[9] == null ? 0 : (Integer.parseInt(tuple[9].toString())));
	    	 dashboardCount.setInvgtCount(tuple[10] == null ? 0 :(Integer.parseInt(tuple[10].toString())));
	    	 
	    	 list.add(dashboardCount);
	     }
	     
	      
		return list;
	}

	
}
