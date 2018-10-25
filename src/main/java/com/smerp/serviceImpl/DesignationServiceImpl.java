package com.smerp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.master.repository.DesiginationDao;
import com.smerp.model.admin.Desigination;
import com.smerp.service.master.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesiginationDao desiginationDao;
	
	@Override
	public List<Desigination> findAll() {
		return null;
	}

}
