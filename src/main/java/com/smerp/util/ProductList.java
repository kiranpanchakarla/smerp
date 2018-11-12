package com.smerp.util;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component
public class ProductList {
	
	
	public   Map<String, String> getManageProductBy() {
        Map<String, String> manageProductBy = new TreeMap<String, String>();
        manageProductBy.put("None", "None");
        manageProductBy.put("Serial Numbers", "Serial Numbers");
        manageProductBy.put("Batches", "Batches");
        return manageProductBy;
    }
	
	
	public   Map<String, String> getProductTaxCategory() {
        Map<String, String> productTaxCategory = new TreeMap<String, String>();
        productTaxCategory.put("Regular", "Regular");
        productTaxCategory.put("Nil Rated", "Nil Rated");
        productTaxCategory.put("Exempt", "Exempt");
        return productTaxCategory;
    } 
	
	
	public   Map<String, String> getProductType() {
        Map<String, String> productType = new TreeMap<String, String>();
        productType.put("Raw material", "Raw material");
        productType.put("Capital Goods", "Capital Goods");
        productType.put("Finished Goods", "Finished Goods");
        return productType;
    } 
	
	
	public   Map<String, String> getTaxCategory() {
        Map<String, String> taxCategory = new TreeMap<String, String>();
        taxCategory.put("Regular", "Regular");
        taxCategory.put("Nil Rated", "Nil Rated");
        taxCategory.put("Exempt", "Exempt");
        return taxCategory;
    } 

	
	public   Map<String, String> getValuationMethod() {
        Map<String, String> valuationMethod = new TreeMap<String, String>();
        valuationMethod.put("Standard", "Standard");
        valuationMethod.put("Moving Average", "Moving Average");
        return valuationMethod;
    } 

}
