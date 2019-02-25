package com.smerp.service.master;

public interface DocNumAutoGenerateService {
	Integer getCountByDocType(String docType);
	boolean updateCountByDocType(String docType,Integer count);
}
