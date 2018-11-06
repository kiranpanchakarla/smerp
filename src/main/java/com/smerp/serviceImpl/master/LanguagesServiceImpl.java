package com.smerp.serviceImpl.master;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.Languages;
import com.smerp.repository.master.LanguagesRepository;
import com.smerp.service.master.LanguagesService;

@Service
public class LanguagesServiceImpl implements LanguagesService {

	@Autowired
	LanguagesRepository languagesRepository;
	
	@Override
	public Languages save(Languages languages) {
		languagesRepository.save(languages);
		return languages;
	}

	@Override
	public List<Languages> findAll() {
		return languagesRepository.findAll();
	}

	@Override
	public Languages findById(int id) {
		return languagesRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		languagesRepository.deleteById(id);
	}

	@Override
	public Languages getInfo(int id) {
		// TODO Auto-generated method stub
		return languagesRepository.findById(id);
	}

}
