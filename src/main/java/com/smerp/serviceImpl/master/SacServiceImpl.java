package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.SACCode;
import com.smerp.repository.master.SacRepository;
import com.smerp.service.master.SacService;

@Service
public class SacServiceImpl implements SacService {

	private static final Logger logger = LogManager.getLogger(SacServiceImpl.class);

	@Autowired
	SacRepository sacRepository;

	@Override
	public List<SACCode> findAll() {
		logger.info("inside SacServiceImpl findAll method");
		return sacRepository.findAll();
	}

	@Override
	public SACCode save(SACCode savcode) {
		logger.info("inside SacServiceImpl save method");
		try {
			sacRepository.save(savcode);
		} catch (Exception ex) {
		}
		return savcode;
	}

	@Override
	public SACCode findById(int id) {
		logger.info("inside SacServiceImpl find by id method");
		return sacRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		logger.info("inside SacServiceImpl delete method");
		sacRepository.deleteById(id);
	}

	@Override
	public SACCode getInfo(int id) {
		logger.info("inside SacServiceImpl getInfo method");
		return sacRepository.findById(id);
	}

	@Override
    public List<String> findAllSacCodes() {
        
        return sacRepository.findAllSacCodes();
    }
	
	@Override
    public SACCode findBySacCode(String sacCode) {
        
        return sacRepository.findBySacCode(sacCode);
    }

	@Override
	public SACCode findByCode(String name) {
		// TODO Auto-generated method stub
		return sacRepository.findByCode(name);
	}
	
}
