package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.States;
import com.smerp.repository.master.StateRepository;
import com.smerp.service.master.StatesService;

@Service
public class StatesServiceImpl implements StatesService {

	private static final Logger logger = LogManager.getLogger(StatesServiceImpl.class);

	@Autowired
	StateRepository stateRepository;

	@Override
	public States save(States states) {
		logger.info("inside StatesServiceImpl save method");
		stateRepository.save(states);
		return states;
	}

	@Override
	public List<States> findAll() {
		logger.info("inside StatesServiceImpl FindAll method");
		return stateRepository.findAll();
	}

	@Override
	public States findById(int id) {
		logger.info("inside StatesServiceImpl find by id method");
		return stateRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		logger.info("inside StatesServiceImpl delete method");
		stateRepository.deleteById(id);
	}

	@Override
	public States getInfo(int id) {
		logger.info("inside StatesServiceImpl getInfo method");
		return stateRepository.findById(id);
	}

	@Override
	public States findByName(String name) {
		// TODO Auto-generated method stub
		return stateRepository.findByName(name);
	}

	@Override
	public States findByCode(String name) {
		// TODO Auto-generated method stub
		return stateRepository.findByCode(name);
	}

}
