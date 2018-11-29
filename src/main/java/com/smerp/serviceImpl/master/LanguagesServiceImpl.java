package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.Languages;
import com.smerp.repository.master.LanguagesRepository;
import com.smerp.service.master.LanguagesService;

@Service
public class LanguagesServiceImpl implements LanguagesService {

	private static final Logger logger = LogManager.getLogger(LanguagesServiceImpl.class);

	@Autowired
	LanguagesRepository languagesRepository;

	@Override
	public Languages save(Languages languages) {
		logger.info("inside LanguagesServiceImpl save method");
		languagesRepository.save(languages);
		return languages;
	}

	@Override
	public List<Languages> findAll() {
		logger.info("inside LanguagesServiceImpl findAll method");
		return languagesRepository.findAll();
	}

	@Override
	public Languages findById(int id) {
		logger.info("inside LanguagesServiceImpl find by id method");
		return languagesRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		logger.info("inside LanguagesServiceImpl delete method");
		languagesRepository.deleteById(id);
	}

	@Override
	public Languages getInfo(int id) {
		logger.info("inside LanguagesServiceImpl getInfo method");
		return languagesRepository.findById(id);
	}

	@Override
	public Languages findByName(String name) {
		 
		return languagesRepository.findByName(name);
	}

}
