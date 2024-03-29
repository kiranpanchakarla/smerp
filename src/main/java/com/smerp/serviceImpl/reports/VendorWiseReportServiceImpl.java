package com.smerp.serviceImpl.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.reports.MonthsForReport;
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.VendorWiseReportService;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.GetReportFilterResult;

@Service
public class VendorWiseReportServiceImpl implements VendorWiseReportService {

	private static final Logger logger = LogManager.getLogger(VendorWiseReportServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GetReportFilterResult getSearchFilterResult;

	@Override
	public ArrayList<Object[]> getPOList() {

		String qtysql = "select * from vw_vendor_wise_po_report";
		Query query1 = entityManager.createNativeQuery(qtysql);


		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());

		return arrayList;
	}

	@Override
	public List<VendorWiseReport> vendorReportList(ArrayList<Object[]> arrayList) {
		List<VendorWiseReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			VendorWiseReport prolist = new VendorWiseReport();
			prolist.setVendorId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			prolist.setVendorCode(tuple[1].toString());
			prolist.setVendorName(tuple[2].toString());
			prolist.setTotalAmount((double) (tuple[3] == null ? 0 : (Double.parseDouble(tuple[3].toString()))));
			prolist.setAPR((double) (tuple[4] == null ? 0 : (Double.parseDouble(tuple[4].toString()))));
			prolist.setMAY((double) (tuple[5] == null ? 0 : (Double.parseDouble(tuple[5].toString()))));
			prolist.setJUN((double) (tuple[6] == null ? 0 : (Double.parseDouble(tuple[6].toString()))));
			prolist.setJUL((double) (tuple[7] == null ? 0 : (Double.parseDouble(tuple[7].toString()))));
			prolist.setAUG((double) (tuple[8] == null ? 0 : (Double.parseDouble(tuple[8].toString()))));
			prolist.setSEP((double) (tuple[9] == null ? 0 : (Double.parseDouble(tuple[9].toString()))));
			prolist.setOCT((double) (tuple[10] == null ? 0 : (Double.parseDouble(tuple[10].toString()))));
			prolist.setNOV((double) (tuple[11] == null ? 0 : (Double.parseDouble(tuple[11].toString()))));
			prolist.setDEC((double) (tuple[12] == null ? 0 : (Double.parseDouble(tuple[12].toString()))));
			prolist.setJAN((double) (tuple[13] == null ? 0 : (Double.parseDouble(tuple[13].toString()))));
			prolist.setFEB((double) (tuple[14] == null ? 0 : (Double.parseDouble(tuple[14].toString()))));
			prolist.setMAR((double) (tuple[15] == null ? 0 : (Double.parseDouble(tuple[15].toString()))));
			reportList.add(prolist);
		}

		return reportList;
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
	public ArrayList<Object[]> getINVList() {
		String qtysql = "select * from vw_vendor_wise_invoive_report";
		Query query1 = entityManager.createNativeQuery(qtysql);


		ArrayList<Object[]> arrayList = new ArrayList<>();
		arrayList.addAll(query1.getResultList());

		return arrayList;
	}

	@Override
	public List<VendorWiseReport> vendorReportAnnualList(ArrayList<Object[]> arrayList) {
		List<VendorWiseReport> reportList = new ArrayList<>();
		for (Object[] tuple : arrayList) {
			VendorWiseReport prolist = new VendorWiseReport();
			prolist.setVendorId(tuple[0] == null ? 0 : ((Integer) tuple[0]).intValue());
			prolist.setVendorCode(tuple[1].toString());
			prolist.setVendorName(tuple[2].toString());
			prolist.setTotalAmount((double) (tuple[3] == null ? 0 : (Double.parseDouble(tuple[3].toString()))));
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

}
