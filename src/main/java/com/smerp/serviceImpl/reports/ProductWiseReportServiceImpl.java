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
import com.smerp.model.reports.ProductWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.ProductWiseReportService;
import com.smerp.util.GetReportFilterResult;

@Service
public class ProductWiseReportServiceImpl implements ProductWiseReportService {

	private static final Logger logger = LogManager.getLogger(ProductWiseReportServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GetReportFilterResult getSearchFilterResult;
	
	 
	@Override
	public ArrayList<Object[]> getPOList() {
		String qtysql = "select * from vw_product_wise_po_report";
		Query query1 = entityManager.createNativeQuery(qtysql);


		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());

		return arrayList;
	}
	@Override
	public ArrayList<Object[]> getINVList() {
		String qtysql = "select * from vw_product_wise_inv_report";
		Query query1 = entityManager.createNativeQuery(qtysql);

		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());

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

		Query query = entityManager.createNativeQuery(resultQuery);
		arrayList = new ArrayList<>();
		arrayList.addAll(query.getResultList());

	  return arrayList;

	}
	@Override
	public List<ProductWiseReport> vendorReportList(ArrayList<Object[]> arrayList) {
		List<ProductWiseReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			ProductWiseReport prolist = new ProductWiseReport();
			 prolist.setProductId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setProductName(tuple[1].toString());
			 prolist.setDescription(tuple[2].toString());
			 prolist.setTotalQty((double) (tuple[3] == null ? 0 : (Double.parseDouble(tuple[3].toString()))));
			 prolist.setTotalAmount((double) (tuple[4] == null ? 0 : (Double.parseDouble(tuple[4].toString()))));
			 prolist.setAprQty((double) (tuple[5] == null ? 0 : (Double.parseDouble(tuple[5].toString()))));
			 prolist.setAprAmt((double) (tuple[6] == null ? 0 : (Double.parseDouble(tuple[6].toString()))));
			 prolist.setMayQty((double) (tuple[7] == null ? 0 : (Double.parseDouble(tuple[7].toString()))));
			 prolist.setMayAmt((double) (tuple[8] == null ? 0 : (Double.parseDouble(tuple[8].toString()))));
			 prolist.setJunQty((double) (tuple[9] == null ? 0 : (Double.parseDouble(tuple[9].toString()))));
			 prolist.setJunAmt((double) (tuple[10] == null ? 0 : (Double.parseDouble(tuple[10].toString()))));
			 prolist.setJulQty((double) (tuple[11] == null ? 0 : (Double.parseDouble(tuple[11].toString()))));
			 prolist.setJulAmt((double) (tuple[12] == null ? 0 : (Double.parseDouble(tuple[12].toString()))));
			 prolist.setAugQty((double) (tuple[13] == null ? 0 : (Double.parseDouble(tuple[13].toString()))));
			 prolist.setAugAmt((double) (tuple[14] == null ? 0 : (Double.parseDouble(tuple[14].toString()))));
			 prolist.setSeptQty((double) (tuple[15] == null ? 0 : (Double.parseDouble(tuple[15].toString()))));
			 prolist.setSeptAmt((double) (tuple[16] == null ? 0 : (Double.parseDouble(tuple[16].toString()))));
			 prolist.setOctQty((double) (tuple[17] == null ? 0 : (Double.parseDouble(tuple[17].toString()))));
			 prolist.setOctAmt((double) (tuple[18] == null ? 0 : (Double.parseDouble(tuple[18].toString()))));
			 prolist.setNovQty((double) (tuple[19] == null ? 0 : (Double.parseDouble(tuple[19].toString()))));
			 prolist.setNovAmt((double) (tuple[20] == null ? 0 : (Double.parseDouble(tuple[20].toString()))));
			 prolist.setDecQty((double) (tuple[21] == null ? 0 : (Double.parseDouble(tuple[21].toString()))));
			 prolist.setDecAmt((double) (tuple[22] == null ? 0 : (Double.parseDouble(tuple[22].toString()))));
			 prolist.setJanQty((double) (tuple[23] == null ? 0 : (Double.parseDouble(tuple[23].toString()))));
			 prolist.setJanAmt((double) (tuple[24] == null ? 0 : (Double.parseDouble(tuple[24].toString()))));
			 prolist.setFebQty((double) (tuple[25] == null ? 0 : (Double.parseDouble(tuple[25].toString()))));
			 prolist.setFebAmt((double) (tuple[26] == null ? 0 : (Double.parseDouble(tuple[26].toString()))));
			 prolist.setMarQty((double) (tuple[27] == null ? 0 : (Double.parseDouble(tuple[27].toString()))));
			 prolist.setMarAmt((double) (tuple[28] == null ? 0 : (Double.parseDouble(tuple[28].toString()))));
			reportList.add(prolist);
		}

		return reportList;
	}
	@Override
	public ArrayList<Object[]> searchFilterBySelectionForAnnualReports(SearchFilter searchFilter, String typeOf) {
		if (searchFilter.getToDate() == null) {
			searchFilter.setToDate(new Date());
		}

		searchFilter.setTypeOf(typeOf);
		ArrayList<Object[]> arrayList = new ArrayList<>();

		String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelectionForAnnualReports(searchFilter);

		Query query = entityManager.createNativeQuery(resultQuery);
		arrayList = new ArrayList<>();
		arrayList.addAll(query.getResultList());

	  return arrayList;
	}
	@Override
	public List<ProductWiseReport> annualReportList(ArrayList<Object[]> arrayList) {
		List<ProductWiseReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			ProductWiseReport prolist = new ProductWiseReport();
			 prolist.setProductId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			 prolist.setProductName(tuple[1].toString());
			 prolist.setDescription(tuple[2].toString());
			 prolist.setTotalQty((double) (tuple[3] == null ? 0 : (Double.parseDouble(tuple[3].toString()))));
			 prolist.setTotalAmount((double) (tuple[4] == null ? 0 : (Double.parseDouble(tuple[4].toString()))));
			 
			reportList.add(prolist);
		}

		return reportList;
	}

}
