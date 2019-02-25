package com.smerp.serviceImpl.emailids;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.repository.emailids.EmailIdRepository;
import com.smerp.service.emailids.EmailIdService;

@Service
public class EmailIdServiceImpl implements EmailIdService {

	private static final Logger logger = LogManager.getLogger(EmailIdServiceImpl.class);
	
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	@Autowired
	EmailIdRepository emailIdRepository;
	
	@Override
	public String getToEmailIds(String moduleName, String operation) {
		
		
		// TODO Auto-generated method stub
		return emailIdRepository.findToEmailIds(moduleName, operation);
	}

	@Override
	public String getCCEmailIds(String moduleName, String operation) {

		 
		return emailIdRepository.findCCEmailIds(moduleName, operation);
	}

	@Override
	public String getToYMLEmailIds(String moduleName, String operation) {
		// TODO Auto-generated method stub
		return emailIdRepository.getToYMLEmailIds(moduleName, operation);
	}


	/*@Override
	public String getBCCEmailIds(String moduleName, String operation) {
		// TODO Auto-generated method stub
		return emailIdRepository.findBCCEmailIds(moduleName, operation);
	}*/

}
