package com.smerp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.model.search.SearchFilter;
import com.smerp.service.master.PlantService;

@Component
public class GetReportFilterResult {

	private static final Logger logger = LogManager.getLogger(GetReportFilterResult.class);
	
	@Autowired
	private PlantService plantService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	CheckUserPermissionUtil checkUserPermissionUtil;
	
	
	public String getQueryBysearchFilterSelectionForReports(SearchFilter searchFilter){
		String resultQuery = "",searchByQuery="";
		
		if(!searchFilter.getFieldName().isEmpty() && !searchFilter.getSearchBy().equals("select")) {
			String dbSearchByColumnName = SearchFilterMapStatusEnum.DB_COLUMN.get(SearchFilterMapStatusEnum.UI_COLUMN.get(searchFilter.getSearchBy()));
			searchByQuery = searchByQuery +"TRIM(LOWER(sfq."+dbSearchByColumnName+"))='"+searchFilter.getFieldName().toLowerCase().trim()+"'";
		}
		
		 
		
		String dateSelectionQuery="";
		if(searchFilter.getFromDate()!=null && !searchFilter.getFromDate().equals("") && searchFilter.getToDate()!=null && !searchFilter.getToDate().equals("")) {
		
			String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(searchFilter.getFromDate());
			String toDate = new SimpleDateFormat("yyyy-MM-dd").format(searchFilter.getToDate());
			
			String dbDateSelectColumnName =  EnumSearchFilter.DATE.getStatus();
			dateSelectionQuery = dateSelectionQuery + "sfq."+dbDateSelectColumnName+" "+EnumSearchFilter.BETWEEN.getStatus()+" '"+fromDate+"' " + EnumSearchFilter.AND.getStatus()+" '"+toDate + "' ";
		}
		
		
		
		if(!searchByQuery.isEmpty() && dateSelectionQuery.isEmpty()) {
			searchByQuery = EnumSearchFilter.WHERE.getStatus() + " " + searchByQuery  +" ";
		}
		
		if(!searchByQuery.isEmpty() && !dateSelectionQuery.isEmpty()) {
			searchByQuery = EnumSearchFilter.WHERE.getStatus() + " " + searchByQuery +" "+ EnumSearchFilter.AND.getStatus() +" " + dateSelectionQuery;
		}
		
		if(searchByQuery.isEmpty() && !dateSelectionQuery.isEmpty()) {
			searchByQuery = EnumSearchFilter.WHERE.getStatus() + " " + dateSelectionQuery  +" ";
		}
		
		if(searchFilter.getTypeOf().equals(EnumSearchFilter.POREPORTVENDOR.getStatus()) || searchFilter.getTypeOf().equals(EnumSearchFilter.INVREPORTVENDOR.getStatus())) {
			resultQuery = "SELECT sfq.vendor_id,\r\n" + 
					"    sfq.vendor_code,\r\n" + 
					"    sfq.name,\r\n" + 
					"    sum(sfq.total_amount) AS annual,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'APR'), 0) AS april,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAY'), 0) AS may,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUN'), 0) AS june,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUL'), 0) AS july,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'AUG'), 0) AS august,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'SEPT'), 0) AS september,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'OCT'), 0) AS october,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'NOV'), 0) AS november,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'DEC'), 0) AS december,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JAN'), 0) AS january,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'FEB'), 0) AS february,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAR'), 0) AS march\r\n" + 
					"     \r\n" + 
					"   FROM "
			               + searchFilter.getTypeOf() +" sfq " + searchByQuery +
			               "group by vendor_id,vendor_code,name\r\n" + 
			               "order by vendor_id;";
		}
		
		if(searchFilter.getTypeOf().equals(EnumSearchFilter.POREPORTPLANT.getStatus()) || searchFilter.getTypeOf().equals(EnumSearchFilter.INVREPORTPLANT.getStatus())) {
			resultQuery = "SELECT sfq.warehouse,\r\n" + 
					"    sfq.plant_name,\r\n" + 
					"    sum(sfq.total_amount) AS annual,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'APR'), 0) AS april,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAY'), 0) AS may,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUN'), 0) AS june,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUL'), 0) AS july,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'AUG'), 0) AS august,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'SEPT'), 0) AS september,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'OCT'), 0) AS october,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'NOV'), 0) AS november,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'DEC'), 0) AS december,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JAN'), 0) AS january,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'FEB'), 0) AS february,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAR'), 0) AS march\r\n" + 
					"     \r\n" + 
					"   FROM "
			               + searchFilter.getTypeOf() +" sfq " + searchByQuery +
			               "group by warehouse,plant_name\r\n" + 
			               "order by warehouse;";
		}
		
		if(searchFilter.getTypeOf().equals(EnumSearchFilter.POREPORTPRODUCT.getStatus()) || searchFilter.getTypeOf().equals(EnumSearchFilter.INVREPORTPRODUCT.getStatus())) {
			resultQuery = "SELECT \r\n" + 
					"    sfq.product_id,\r\n" + 
					"    sfq.product_number,\r\n" + 
					"    sfq.description,\r\n" + 
					"    COALESCE(sum(sfq.total_qty), 0) AS total_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount), 0) AS total_amount,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'APR'), 0) AS apr_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'APR'), 0) AS apr_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAY'), 0) AS may_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAY'), 0) AS may_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUN'), 0) AS jun_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUN'), 0) AS jun_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUL'), 0) AS jul_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JUL'), 0) AS jul_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'AUG'), 0) AS aug_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'AUG'), 0) AS aug_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'SEPT'), 0) AS sept_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'SEPT'), 0) AS sept_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'OCT'), 0) AS oct_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'OCT'), 0) AS oct_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'NOV'), 0) AS nov_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'NOV'), 0) AS nov_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'DEC'), 0) AS dec_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'DEC'), 0) AS dec_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JAN'), 0) AS jan_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'JAN'), 0) AS jan_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'FEB'), 0) AS feb_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'FEB'), 0) AS feb_amt,\r\n" + 
					"    COALESCE(sum(sfq.total_qty) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAR'), 0) AS mar_qty,\r\n" + 
					"    COALESCE(sum(sfq.total_amount) FILTER (WHERE to_char(sfq.created_date, 'MON') = 'MAR'), 0) AS mar_amt\r\n" + 
					"     \r\n" + 
					"   FROM "
			               + searchFilter.getTypeOf() +" sfq " + searchByQuery +
			               " group by sfq.product_id,sfq.product_number,sfq.description\r\n" + 
			               "order by sfq.product_id;";
		}
		
		
		
		logger.info(resultQuery);
	
		return resultQuery;
	}
	 
	
}