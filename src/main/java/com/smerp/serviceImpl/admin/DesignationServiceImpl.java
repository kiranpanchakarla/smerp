package com.smerp.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Desigination;
import com.smerp.repository.admin.DesiginationRepository;
import com.smerp.service.admin.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesiginationRepository desiginationDao;
	
	@Override
	public List<Desigination> findAll() {
		return null;
	}

}
