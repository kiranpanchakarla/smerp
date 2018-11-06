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
		return sacRepository.findAll();
	}
	@Override
	public SACCode save(SACCode savcode) {
		try {
		sacRepository.save(savcode);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return savcode;
	}
	@Override
	public SACCode findById(int id) {
		return sacRepository.findById(id);
	}
	@Override
	public void delete(int id) {
		sacRepository.deleteById(id);
	}
	@Override
	public SACCode getInfo(int id) {
 		return sacRepository.findById(id);
	}

}
