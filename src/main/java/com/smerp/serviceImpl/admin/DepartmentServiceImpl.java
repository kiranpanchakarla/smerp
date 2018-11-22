package com.smerp.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Department;
import com.smerp.repository.admin.DepartmentRepository;
import com.smerp.service.admin.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

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

}
