package com.smerp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.service.master.DocNumAutoGenerateService;

@Component
public class DocNumberGenerator extends Thread {
	@Autowired
	private DocNumAutoGenerateService docNumAutoGenerateService;
	
	private static final Logger logger = LogManager.getLogger(DocNumberGenerator.class);
	
	public synchronized Integer getCountByDocType(String docType){ // This Synchronized method used by PR,RFQ,PO
		logger.info("getCountByDocType() Started..");
		Integer	docCount = docNumAutoGenerateService.getCountByDocType(docType);
		logger.info("Count value:"+docCount);
		logger.info("docNumAutoGenerate status-->"+ docNumAutoGenerateService.updateCountByDocType(docType,docCount+1));
		logger.info("getCountByDocType() Ended..");
    return docCount+1;
  }
	
	public synchronized Integer getDocCountByDocType(String docType){ // This Synchronized method used by GR,GRE,Inv,CM
		Integer	docCount = docNumAutoGenerateService.getCountByDocType(docType);
		logger.info("Count value:"+docCount);
		logger.info("docNumAutoGenerate status-->"+ docNumAutoGenerateService.updateCountByDocType(docType,docCount+1));
    return docCount+1;
  }
	
	public synchronized Integer getDocNoCountByDocType(String docType){ // This Synchronized method used by IGR,IGI,IGT
		Integer	docCount = docNumAutoGenerateService.getCountByDocType(docType);
		logger.info("Count value:"+docCount);
		logger.info("docNumAutoGenerate status-->"+ docNumAutoGenerateService.updateCountByDocType(docType,docCount+1));
    return docCount+1;
  }
}
