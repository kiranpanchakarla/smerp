package com.smerp.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Module;
import com.smerp.repository.admin.ModuleRepository;
import com.smerp.service.admin.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Override
	public List<Module> findAll() {
		// TODO Auto-generated method stub
		return moduleRepository.findAll();
	}

}
