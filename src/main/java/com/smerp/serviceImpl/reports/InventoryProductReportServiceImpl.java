package com.smerp.serviceImpl.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.reports.InventoryGoodsIssueReport;
import com.smerp.model.reports.InventoryProductReport;
import com.smerp.model.reports.InventoryWarehouseReport;
import com.smerp.model.reports.ProductWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.InventoryProductReportService;
import com.smerp.util.GetReportFilterResult;
@Service
public class InventoryProductReportServiceImpl implements InventoryProductReportService {
	
private static final Logger logger = LogManager.getLogger(InventoryProductReportServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GetReportFilterResult getSearchFilterResult;

	@Override
	public ArrayList<Object[]> getProductList() {
		String qtysql = "select * from vw_inv_stock_qty_report";
		Query query1 = entityManager.createNativeQuery(qtysql);

		logger.info("vw_inv_stock_qty_report SQL ----> " + qtysql);

		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());

		return arrayList;
	}

	@Override
	public ArrayList<Object[]> searchFilterBySelection(SearchFilter searchFilter, String typeOf) {
		if (searchFilter.getToDate() == null) {
			searchFilter.setToDate(new Date());
		}

		searchFilter.setTypeOf(typeOf);
		ArrayList<Object[]> arrayList = new ArrayList<>();

		String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelectionForInvProductReports(searchFilter);

		Query query = entityManager.createNativeQuery(resultQuery);
		arrayList = new ArrayList<>();
		arrayList.addAll(query.getResultList());

	  return arrayList;
	}

	@Override
	public List<InventoryProductReport> productReportList(ArrayList<Object[]> arrayList) {
		List<InventoryProductReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			InventoryProductReport prolist = new InventoryProductReport();
			 prolist.setProductId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setProductName(tuple[1].toString());
			 prolist.setPlantId(tuple[2] == null ? 0 : ((Integer) tuple[2]).intValue());
			 prolist.setPlantName(tuple[3].toString());
			 prolist.setDescription(tuple[4].toString());
			 prolist.setProductGroup(tuple[5].toString());
			 prolist.setUomName(tuple[6].toString());
			 prolist.setInStockQty((double) (tuple[7] == null ? 0 : (Double.parseDouble(tuple[7].toString()))));
			 prolist.setProductCost((double) (tuple[8] == null ? 0 : (Double.parseDouble(tuple[8].toString()))));
			 prolist.setStockValue((double) (tuple[9] == null ? 0 : (Double.parseDouble(tuple[9].toString()))));
			 
			reportList.add(prolist);
		}

		return reportList;
	}

	@Override
	public List<InventoryWarehouseReport> warehouseReportList(ArrayList<Object[]> arrayList) {
		List<InventoryWarehouseReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			InventoryWarehouseReport prolist = new InventoryWarehouseReport();
			 prolist.setProductId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setProductName(tuple[1].toString());
			 prolist.setDescription(tuple[2].toString());
			 prolist.setProductGroup(tuple[3].toString());
			 prolist.setUomName(tuple[4].toString());
			 prolist.setInStockQty((double) (tuple[5] == null ? 0 : (Double.parseDouble(tuple[5].toString()))));
			 prolist.setMadurawada((double) (tuple[6] == null ? 0 : (Double.parseDouble(tuple[6].toString()))));
			 prolist.setYelamanchili((double) (tuple[7] == null ? 0 : (Double.parseDouble(tuple[7].toString()))));
			 
			reportList.add(prolist);
		}

		return reportList;
	}

	@Override
	public List<InventoryGoodsIssueReport> invGIReportList(ArrayList<Object[]> arrayList) {
		List<InventoryGoodsIssueReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			InventoryGoodsIssueReport prolist = new InventoryGoodsIssueReport();
			 prolist.setDocId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setDocnumber(tuple[1].toString());
			 prolist.setDocDate(tuple[2].toString());
			 prolist.setProductName(tuple[3].toString());
			 prolist.setDescription(tuple[4].toString());
			 prolist.setProductGroup(tuple[5].toString());
			 prolist.setPlantName(tuple[6].toString());
			 prolist.setRequiredQty(tuple[7] == null ? 0 : ((Integer) tuple[7]).intValue());
			 prolist.setDepartmentName(tuple[8].toString());
			 prolist.setRemarks(tuple[9].toString()); 
			 
			reportList.add(prolist);
		}

		return reportList;
	}

}
