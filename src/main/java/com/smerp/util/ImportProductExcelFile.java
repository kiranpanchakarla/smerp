package com.smerp.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.model.inventory.Product;
import com.smerp.repository.inventory.ProductRepository;

@Component
public class ImportProductExcelFile {
	
	@Autowired
	ProductRepository productRepository;
	
	private static final Logger logger = LogManager.getLogger(ImportProductExcelFile.class);
	
	public boolean importExcelDataTODB(String filePath) {
		if(filePath!=null) {
			List sheetData = new ArrayList();
			 
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(filePath);
				Workbook workbook;
				Sheet sheet;
					try {
						workbook = WorkbookFactory.create(fis);
						int noOfSheets = workbook.getNumberOfSheets();
						sheet = workbook.getSheetAt(0);
						Iterator rows = sheet.rowIterator();
						Row headerRow = (Row) rows.next();	//Removes the header row
						short maxColIdx = headerRow.getLastCellNum();
						while (rows.hasNext()) {
							Row row = (Row) rows.next();
							List data = new ArrayList();
							short minColIdx = row.getFirstCellNum();
							for(short colIdx=minColIdx ; colIdx<maxColIdx ; colIdx++) {
							     Cell cell = row.getCell(colIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
							     data.add(cell);
							}
							sheetData.add(data);
							}
					} catch (Exception e) {
						logger.info("Error in fileImport() of AdminController" + e);
						e.printStackTrace();
					} finally {
						if (fis != null) {
							fis.close();
						}
					}
			} catch (Exception e) {
				logger.info("Error in AdminController of fileImport:"+e);
				e.printStackTrace();
			} 
			insertExelData(sheetData);
        }else {
        	logger.info("Error in AdminController of fileImport");
        	return false;
        }
		return true;
	}
	
	private void insertExelData(List sheetData) {
		List<Product> listOfproducts = new ArrayList<Product>();
		
		for(int i=0;i<sheetData.size();i++) {
			List row = (List) sheetData.get(i);
			Product product = new Product();
			
				product.setProductNo(row.get(0).toString());
				product.setDescription(row.get(1).toString());
				
				//ProductType productType = new ProductType();
			
				//product.setProductGroup(row.get(2).toString());
				
			listOfproducts.add(product);
		}
		//productRepository.saveAll(listOfproducts);
	}

}
