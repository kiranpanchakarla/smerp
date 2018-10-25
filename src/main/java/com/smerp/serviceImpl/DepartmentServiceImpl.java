package com.smerp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.master.repository.DepartmentDao;
import com.smerp.model.admin.Department;
import com.smerp.service.master.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}

}
