package com.smerp.serviceImpl.emailids;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.ProductQuantity;
import com.smerp.model.emailids.EmailId;
import com.smerp.repository.emailids.EmailIdRepository;
import com.smerp.service.emailids.EmailIdService;
import com.smerp.serviceImpl.admin.DashboardCountServiceImpl;
import com.sun.jndi.cosnaming.IiopUrl.Address;

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
	public String getBCCEmailIds(String moduleName, String operation) {
		// TODO Auto-generated method stub
		return emailIdRepository.findBCCEmailIds(moduleName, operation);
	}

}
