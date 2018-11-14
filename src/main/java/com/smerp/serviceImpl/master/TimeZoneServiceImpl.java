package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.TimeZone;
import com.smerp.repository.master.TimeZoneRepository;
import com.smerp.service.master.TimeZoneService;

@Service
public class TimeZoneServiceImpl implements TimeZoneService {

	private static final Logger logger = LogManager.getLogger(TimeZoneServiceImpl.class);

	@Autowired
	TimeZoneRepository timeZoneRepository;

	@Override
	public TimeZone save(TimeZone timeZone) {
		logger.info("inside TimeZoneServiceImpl save method");
		timeZoneRepository.save(timeZone);
		return timeZone;
	}

	@Override
	public List<TimeZone> findAll() {
		logger.info("inside TimeZoneServiceImpl Find All method");
		return timeZoneRepository.findAll();
	}

	@Override
	public TimeZone findById(int id) {
		logger.info("inside TimeZoneServiceImpl Find by id method");
		return timeZoneRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		logger.info("inside TimeZoneServiceImpl delete method");
		timeZoneRepository.deleteById(id);
	}

	@Override
	public TimeZone getInfo(int id) {
		logger.info("inside TimeZoneServiceImpl getInfo method");
		return timeZoneRepository.findById(id);
	}

}
