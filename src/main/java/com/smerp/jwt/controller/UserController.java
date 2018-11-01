package com.smerp.jwt.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smerp.model.admin.Company;
import com.smerp.model.admin.Department;
import com.smerp.model.admin.Desigination;
import com.smerp.model.admin.User;
import com.smerp.service.UserService;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.admin.DesignationService;
import com.smerp.service.master.CurrencyServices;
import com.smerp.service.master.RoleService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	CurrencyServices currencyServices;

	@Autowired
	DesignationService designationService;

	@Autowired
	CompanyServices companyServices;

	@Autowired
	RoleService roleService;

	@GetMapping("/create")
	private String createPage(Model model) {
		logger.info("Inside UserController createPage Method");
		try {
			Company company = getComapnyIdFromSession();
			model.addAttribute("user", new User());
			model.addAttribute("department", departmentMap());
			model.addAttribute("desigination", desiganationMap());
			model.addAttribute("usersList", userService.findByUsersByCompany(company));
			model.addAttribute("rolesList", roleService.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// model.addAttribute("company", companyServices.findAll() );
		return "user/create";
	}
	
	
	
	
	

	private Company getComapnyIdFromSession() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userService.findByUsername(username);
		return user.getCompany();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(User user) {
		logger.info("Inside user controller save method" + user);
		userService.save(user);
		return "user/create";
	}

	@RequestMapping(value = "/getdeginations", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, Object> getDesignationsList(@RequestParam("id") String id) {
		logger.info("Inside getDesignationsList method "+id);
		List<Desigination> list = designationService.findByDepartmentId(Integer.parseInt(id));
		Map<Integer, Object> map = list.stream()
				.collect(Collectors.toMap(Desigination::getId, Desigination::getDesigination));
		
		logger.info("length  "+list.size());
		return map;
	}

	public Map<Integer, Object> desiganationMap() {
		Company company = getComapnyIdFromSession();
		Map<Integer, Object> map = designationService.findDesignationsByCompanyId(company.getId()).stream()
				.collect(Collectors.toMap(Desigination::getId, Desigination::getDesigination));
		return map;
	}

	//

	public Map<Integer, Object> departmentMap() {
		Company company = getComapnyIdFromSession();
		Map<Integer, Object> map = departmentService.findDepartmentsByCompanyId(company.getId()).stream()
				.collect(Collectors.toMap(Department::getId, Department::getName));
		return map;
	}

	@RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

}
