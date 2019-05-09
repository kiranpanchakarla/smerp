package com.smerp.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SearchFilterMapStatusEnum {
	
			/* UI_Columns  DB_pojo_Columns*/
	REQUESTERFNAME("requesterFName","referenceUser.firstname"),
	REQUESTERLNAME("requesterLName","referenceUser.lastname"),
	VENDORNAME("vendorName","vendor.name"),
	DOCNUMBER("documentNo","docNumber"),
	PLANT("plant","plant.plantName"),
	FROMPLANT("fromplant","fromWarehouse.plantName"),
	STATUS("status","status"),
	POVENDORNAME("vendor_Name","name"),
	POVENDORCODE("vendor_Code","vendor_code"),
	POPLANTNAME("warehouse_Name","plant_name"),
	POPRODUCTNUMBER("product_number","product_number"),
	PODESCRIPTION("description","description"),
	INVPRODUCTNUMBER("product_number","product_no"),
    INVDESCRIPTION("product_description","product_description"),
	INVPRODUCTGROUP("product_group","product_group_description"),
	INVUOM("uom","uom_name"),
	CREATEDDATE("createdDate","createdAt"),
	UPDATEDDATE("updatedDate","updatedAt"),
	PO_YEAR("po_Year","po_year"),
	MADURAWADA("Madurawada","Madurawada"),
	YELAMANCHILI("Yelamanchili","Yelamanchili"),
	INVDOCNUMBER("doc_number","doc_number"),
	INVDOCDATE("doc_date","document_date"),
	INVPLANT("plant","plant_name"),
	INVDEPARTMENT("department","department_name");
	
	private String status;
	private String value;
	
	private SearchFilterMapStatusEnum(String status, String value) {
		this.status = status;
		this.value = value;
	}

    public static final Map<String, SearchFilterMapStatusEnum> UI_COLUMN;
    static {
        Map<String, SearchFilterMapStatusEnum> tmpMap = new HashMap<String, SearchFilterMapStatusEnum>();
        for(SearchFilterMapStatusEnum requestStatusEnum : SearchFilterMapStatusEnum.values()) {
            tmpMap.put(requestStatusEnum.status, requestStatusEnum);
        }
        UI_COLUMN = Collections.unmodifiableMap(tmpMap);
    }
	
    public static final Map<SearchFilterMapStatusEnum, String> DB_COLUMN;
    static {
        Map<SearchFilterMapStatusEnum, String> tmpMap = new HashMap<SearchFilterMapStatusEnum, String>();
        for(SearchFilterMapStatusEnum requestStatusEnum : SearchFilterMapStatusEnum.values()) {
            tmpMap.put(requestStatusEnum, requestStatusEnum.value);
        }
        DB_COLUMN = Collections.unmodifiableMap(tmpMap);
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
