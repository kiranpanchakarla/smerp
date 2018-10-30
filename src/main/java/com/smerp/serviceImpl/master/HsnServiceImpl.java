package com.smerp.serviceImpl.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.master.HSNCode;
import com.smerp.repository.master.HsnRepository;
import com.smerp.service.master.HsnSevice;

@Service
public class HsnServiceImpl implements HsnSevice {
	
	
	@Autowired
	HsnRepository  hsnRepository;

	@Override
	public List<HSNCode> findAll() {
		
		return hsnRepository.findAll();
	}

}