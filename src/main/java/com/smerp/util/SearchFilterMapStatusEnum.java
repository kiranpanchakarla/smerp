package com.smerp.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum SearchFilterMapStatusEnum {
	
			/* UI_columns  DB_Columns*/
	USERNAME("userName","referenceUser.username"),
	VENDORNAME("vendorName","vendor.name"),
	DOCNUMBER("documentNo","docNumber"),
	PLANT("plant","plant.plantName"),
	STATUS("status","status"),
	CREATEDDATE("createdDate","createdAt"),
	UPDATEDDATE("updatedDate","updatedAt");
	
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
