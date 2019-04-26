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
import com.smerp.model.reports.WarehouseWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.WarehouseWiseReportService;
import com.smerp.util.GetReportFilterResult;
@Service
public class WarehouseWiseReportServiceImpl implements WarehouseWiseReportService {

private static final Logger logger = LogManager.getLogger(WarehouseWiseReportServiceImpl.class);
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	@Autowired
	private GetReportFilterResult getSearchFilterResult;
	
	@Override
	public ArrayList<Object[]> getPOList() {
		String qtysql = "select * from vw_plant_wise_po_report";
		Query query1 = entityManager.createNativeQuery(qtysql);

		logger.info("vw_plant_wise_po_report SQL ----> " + qtysql);

		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());

		return arrayList;
	}

	@Override
	public ArrayList<Object[]> getINVList() {
		String qtysql = "select * from vw_plant_wise_inv_report";
		Query query1 = entityManager.createNativeQuery(qtysql);

		logger.info("vw_plant_wise_inv_report SQL ----> " + qtysql);

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

		String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelectionForReports(searchFilter);
		logger.info(resultQuery);

		Query query = entityManager.createNativeQuery(resultQuery);
		arrayList = new ArrayList<>();
		arrayList.addAll(query.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());

	  return arrayList;
	}

	@Override
	public List<WarehouseWiseReport> plantReportList(ArrayList<Object[]> arrayList) {
		List<WarehouseWiseReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			WarehouseWiseReport prolist = new WarehouseWiseReport();
			prolist.setWarehouseId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			prolist.setWarehouseName(tuple[1].toString());
			prolist.setTotalAmount((double) (tuple[2] == null ? 0 : (Double.parseDouble(tuple[2].toString()))));
			prolist.setAPR((double) (tuple[3] == null ? 0 : (Double.parseDouble(tuple[3].toString()))));
			prolist.setMAY((double) (tuple[4] == null ? 0 : (Double.parseDouble(tuple[4].toString()))));
			prolist.setJUN((double) (tuple[5] == null ? 0 : (Double.parseDouble(tuple[5].toString()))));
			prolist.setJUL((double) (tuple[6] == null ? 0 : (Double.parseDouble(tuple[6].toString()))));
			prolist.setAUG((double) (tuple[7] == null ? 0 : (Double.parseDouble(tuple[7].toString()))));
			prolist.setSEP((double) (tuple[8] == null ? 0 : (Double.parseDouble(tuple[8].toString()))));
			prolist.setOCT((double) (tuple[9] == null ? 0 : (Double.parseDouble(tuple[9].toString()))));
			prolist.setNOV((double) (tuple[10] == null ? 0 : (Double.parseDouble(tuple[10].toString()))));
			prolist.setDEC((double) (tuple[11] == null ? 0 : (Double.parseDouble(tuple[11].toString()))));
			prolist.setJAN((double) (tuple[12] == null ? 0 : (Double.parseDouble(tuple[12].toString()))));
			prolist.setFEB((double) (tuple[13] == null ? 0 : (Double.parseDouble(tuple[13].toString()))));
			prolist.setMAR((double) (tuple[14] == null ? 0 : (Double.parseDouble(tuple[14].toString()))));
			reportList.add(prolist);
		}

		return reportList;
	}
	
	 

}
