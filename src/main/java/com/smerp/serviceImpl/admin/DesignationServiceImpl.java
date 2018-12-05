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
	DesiginationRepository designationRepository;

	@Override
	public List<Desigination> findAll() {
		return designationRepository.findAll();
	}

	@Override
	public List<Desigination> findDesignationsByCompanyId(Integer id) {
		return designationRepository.findDesignationsByCompanyId(id);
	}

	@Override
	public List<Desigination> findByDepartmentId(int id) {
		return designationRepository.findByDepartmentId(id);
	}

	@Override
	public Desigination save(Desigination designation) {
		
		return designationRepository.save(designation);
	}

	@Override
	public Desigination findById(int id) {
		return designationRepository.findById(id);
	}

	@Override
	public Desigination getInfo(int id) {
		return designationRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		designationRepository.deleteById(id);
	}

}
