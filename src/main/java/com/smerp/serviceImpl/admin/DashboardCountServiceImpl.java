package com.smerp.serviceImpl.admin;

import java.math.BigInteger;
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
				"    rejected as ( select count(*) as totalCnt8 from tbl_purchase_order where status='Rejected' and is_active=true)\r\n" + 
				"select countWise.totalCnt1 ,openCnt.totalCnt2,cancelledCnt.totalCnt3,draftCnt.totalCnt4,approvedCnt.totalCnt5,convertedToRFQCnt.totalCnt6,completed.totalCnt7,rejected.totalCnt8\r\n" + 
				"from countWise,openCnt,cancelledCnt,draftCnt,approvedCnt,convertedToRFQCnt,completed,rejected  ";
		
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
	    	
	     }
	     
	     logger.info("RFQ Count--------->"+poCount); 
		return poCount;
	}

}
