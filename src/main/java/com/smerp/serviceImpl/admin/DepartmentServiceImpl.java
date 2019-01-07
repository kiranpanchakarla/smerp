package com.smerp.serviceImpl.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Department;
import com.smerp.repository.admin.DepartmentRepository;
import com.smerp.service.admin.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public List<Department> findDepartmentsByCompanyId(Integer id) {
		return departmentRepository.findDepartmentsByCompanyId(id);
	}

	@Override
	public Department save(Department department) {
		departmentRepository.save(department);
		return department;
	}

	@Override
	public Department findById(int id) {
		return departmentRepository.findById(id);
	}

	@Override
	public Department getInfo(int id) {

		return departmentRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		departmentRepository.deleteById(id);

	}

	@Override
	public Department findByName(String name) {
		
		return departmentRepository.findByName(name);
	}

	 

	/* @Override
	public boolean isValid(String name,Integer companyid) {
		boolean isValid = false;
		List<Department> department = departmentRepository.findByName(name);
		
	       int falg=0;
			for(int j=0; j<department.size();j++) {
				logger.info("Department ---> " + department.get(j));
				
				Department dept =  department.get(j);
				if(dept.getCompany().getId().equals(companyid)) {
					falg++;
				}
				
				if(falg>=1) {
					isValid = true;
				}
				
			}  
			
		
		
		return isValid;
	}*/
}
