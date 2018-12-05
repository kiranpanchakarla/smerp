package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.HSNCode;
import com.smerp.repository.master.HsnRepository;
import com.smerp.service.master.HsnService;

@Service
public class HsnServiceImpl implements HsnService {

	private static final Logger logger = LogManager.getLogger(HsnServiceImpl.class);

	@Autowired
	HsnRepository hsnRepository;

	@Override
	public HSNCode save(HSNCode hsncode) {
		logger.info("inside HsnServiceImpl save method");
		hsnRepository.save(hsncode);
		return hsncode;
	}

	@Override
	public List<HSNCode> findAll() {
		logger.info("inside HsnServiceImpl find all method");
		return hsnRepository.findAll();
	}

	@Override
	public HSNCode findById(int id) {
		logger.info("inside HsnServiceImpl find by id method");
		return hsnRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		logger.info("inside HsnServiceImpl delete method");
		hsnRepository.deleteById(id);
	}

	@Override
	public HSNCode getInfo(int id) {
		logger.info("inside HsnServiceImpl getInfo method");
		return hsnRepository.findById(id);
	}

	@Override
	public HSNCode findByCode(String name) {
		// TODO Auto-generated method stub
		return hsnRepository.findByCode(name);
	}

}
