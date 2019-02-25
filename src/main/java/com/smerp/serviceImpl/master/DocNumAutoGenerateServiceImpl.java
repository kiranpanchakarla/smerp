package com.smerp.serviceImpl.master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.master.DocNumAutoGenerate;
import com.smerp.repository.master.DocNumAutoGenerateRepository;
import com.smerp.service.master.DocNumAutoGenerateService;

@Service
public class DocNumAutoGenerateServiceImpl implements DocNumAutoGenerateService {
	
	private static final Logger logger = LogManager.getLogger(DocNumAutoGenerateServiceImpl.class);
	
	@Autowired
	private DocNumAutoGenerateRepository docNumAutoGenerateRepository;
	
	@Override
	public Integer getCountByDocType(String docType) {
		DocNumAutoGenerate docGen = docNumAutoGenerateRepository.findByDocType(docType);
		logger.info("DocNumAutoGenerate-->"+docGen);
		Integer count=0;
		if(docGen!=null) {
			count = docGen.getCount();
		}
		return count;
	}
	
	@Override
	public boolean updateCountByDocType(String docType,Integer count) {
		DocNumAutoGenerate docGen = docNumAutoGenerateRepository.findByDocType(docType);
		docGen.setCount(count);
		docGen = docNumAutoGenerateRepository.save(docGen);
		logger.info("updateCountByDocType-->"+docGen);
		if(docGen!=null) {
			return true;
		}
		return false;
	}
}
