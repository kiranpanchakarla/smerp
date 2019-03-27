package com.smerp.util;

import java.text.SimpleDateFormat;
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
public class GetSearchFilterResult {
	
	private static final Logger logger = LogManager.getLogger(GetSearchFilterResult.class);
	
	@Autowired
	private PlantService plantService;
	
	@PersistenceContext
	private EntityManager entityManager;

	public String getQueryBysearchFilterSelection(SearchFilter searchFilter){
		String resultQuery = "";
		String searchByQuery = "";
		
		if(!searchFilter.getFieldName().isEmpty() && !searchFilter.getSearchBy().equals("select")) {
			String dbSearchByColumnName = SearchFilterMapStatusEnum.DB_COLUMN.get(SearchFilterMapStatusEnum.UI_COLUMN.get(searchFilter.getSearchBy()));
			searchByQuery = searchByQuery +"TRIM(LOWER(sfq."+dbSearchByColumnName+"))='"+searchFilter.getFieldName().toLowerCase().trim()+"'";
		}
		
		String dateSelectionQuery="";
		if(!searchFilter.getDateSelect().isEmpty() && !searchFilter.getDateSelect().equals("select") && searchFilter.getFromDate()!=null &&
				!searchFilter.getFromDate().equals("") && searchFilter.getToDate()!=null && !searchFilter.getToDate().equals("")) {
		
			String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(searchFilter.getFromDate());
			String toDate = new SimpleDateFormat("yyyy-MM-dd").format(searchFilter.getToDate());
			
			String dbDateSelectColumnName = SearchFilterMapStatusEnum.DB_COLUMN.get(SearchFilterMapStatusEnum.UI_COLUMN.get(searchFilter.getDateSelect()));
			dateSelectionQuery = dateSelectionQuery + "sfq."+dbDateSelectColumnName+" "+EnumSearchFilter.BETWEEN.getStatus()+" '"+fromDate+" 00:00:00' and '"+toDate+" 23:59:59' ";
		}
		
		if(!searchByQuery.isEmpty() && !dateSelectionQuery.isEmpty()) {
			searchByQuery = searchByQuery +" "+ EnumSearchFilter.AND.getStatus() +" ";
		}
		
		String oderbyQuery = "";
		
		if(!searchFilter.getSortBy().equals("select")) {
			String dbSortByColumnName = SearchFilterMapStatusEnum.DB_COLUMN.get(SearchFilterMapStatusEnum.UI_COLUMN.get(searchFilter.getSortBy()));
			oderbyQuery = oderbyQuery + "order by sfq."+ dbSortByColumnName;
		}else {
			String dbSortByColumnName = SearchFilterMapStatusEnum.DB_COLUMN.get(SearchFilterMapStatusEnum.UI_COLUMN.get(EnumSearchFilter.CREATEDAT.getStatus()));
			oderbyQuery = "order by sfq."+ dbSortByColumnName;
		}
		
		int plantIds[] = plantService.findPlantIds();
		String plantIdsString = "";
		for(int id = 0; id<plantIds.length; id++) {
			if(id==plantIds.length-1)
				plantIdsString = plantIdsString + plantIds[id];
			else
				plantIdsString = plantIdsString + plantIds[id] +",";
		}
		String isActiveAndPlant="";
		
		if(searchFilter.getIsConvertedDoc()!= null && searchFilter.getIsConvertedDoc().equals("true")) {
			String statusListString = searchFilter.getStatusList().isEmpty() ? "" : "\'" + String.join("\', \'", searchFilter.getStatusList()) + "\'";
			String statusDBColumn = SearchFilterMapStatusEnum.DB_COLUMN.get(SearchFilterMapStatusEnum.UI_COLUMN.get(EnumSearchFilter.STATUS.getStatus()));
			isActiveAndPlant = " sfq.isActive='true' "+EnumSearchFilter.AND.getStatus()+" sfq.plant.id in ("+plantIdsString+") "+EnumSearchFilter.AND.getStatus()+" sfq."+statusDBColumn+" in ("+statusListString+") ";
		}else {
			isActiveAndPlant = " sfq.isActive='true' "+EnumSearchFilter.AND.getStatus()+" sfq.plant.id in ("+plantIdsString+") ";
		}
		if(!searchByQuery.isEmpty() || !dateSelectionQuery.isEmpty()) {
			oderbyQuery =  " "+EnumSearchFilter.AND.getStatus()+" "+ isActiveAndPlant + oderbyQuery;
		} else {
			oderbyQuery = isActiveAndPlant + oderbyQuery;
		}
				
		resultQuery = "select sfq from "+searchFilter.getTypeOf()+" sfq where " + searchByQuery + dateSelectionQuery + oderbyQuery;
		logger.info(resultQuery);
	
		return resultQuery;
	}

	
}
