package com.smerp.serviceImpl.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.master.SACCode;
import com.smerp.repository.master.SacRepository;
import com.smerp.service.master.SacService;

@Service
public class SacServiceImpl implements SacService {
	
	@Autowired
	SacRepository  sacRepository;
	@Override
	public List<SACCode> findAll() {
		// TODO Auto-generated method stub
		return sacRepository.findAll();
	}

}
