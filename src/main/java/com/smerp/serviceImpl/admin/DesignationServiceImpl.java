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
	DesiginationRepository desiginationRepository;
	
	@Override
	public List<Desigination> findAll() {
		return desiginationRepository.findAll();
	}

	@Override
	public List<Desigination> findDesignationsByCompanyId(Integer id) {
		// TODO Auto-generated method stub
		return desiginationRepository.findDesignationsByCompanyId(id);
	}

	@Override
	public List<Desigination> findByDepartmentId(int id) {
		// TODO Auto-generated method stub
		return desiginationRepository.findByDepartmentId(id);
	}

}
