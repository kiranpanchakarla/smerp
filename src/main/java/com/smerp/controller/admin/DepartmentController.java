package com.smerp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smerp.model.admin.Department;
import com.smerp.service.admin.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping("/list")
	public String list() {
		
	List<Department> list=departmentService.findAll();
		
		return null;
	}

}
