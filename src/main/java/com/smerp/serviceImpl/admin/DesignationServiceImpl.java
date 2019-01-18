package com.smerp.serviceImpl.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.admin.Desigination;
import com.smerp.repository.admin.DepartmentRepository;
import com.smerp.repository.admin.DesiginationRepository;
import com.smerp.service.admin.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	
	private static final Logger logger = LogManager.getLogger(DesignationServiceImpl.class);
	
	@Autowired
	DesiginationRepository designationRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;

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

	 @Override
	public boolean isValid(String name, Integer deptId) {
		boolean isValid = false;
		List<Desigination> desigination = designationRepository.findByName(name);
		
	       int falg=0;
			for(int j=0; j<desigination.size();j++) {
				logger.info("Desigination ---> " + desigination.get(j));
				
				Desigination desg =  desigination.get(j);
				if(desg.getDepartment().getId().equals(deptId)) {
					falg++;
				}
				
				if(falg>=1) {
					isValid = true;
				}
				
			}
			 
		return isValid;
	}
 
	@Override
	public List<Desigination> findByName(String name) {
		// TODO Auto-generated method stub
		return designationRepository.findByName(name);
	}

}
