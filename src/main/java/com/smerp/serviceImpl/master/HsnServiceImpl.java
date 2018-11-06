package com.smerp.serviceImpl.master;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.HSNCode;
import com.smerp.repository.master.HsnRepository;
import com.smerp.service.master.HsnService;

@Service
public class HsnServiceImpl implements HsnService {
	
	@Autowired
	HsnRepository hsnRepository;

	@Override
	public HSNCode save(HSNCode hsncode) {
		hsnRepository.save(hsncode);
		return hsncode;
	}

	@Override
	public List<HSNCode> findAll() {
		return hsnRepository.findAll();
	}

	@Override
	public HSNCode findById(int id) {
		return hsnRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		hsnRepository.deleteById(id);
	}

	@Override
	public HSNCode getInfo(int id) {
 		return hsnRepository.findById(id);
	}

}
