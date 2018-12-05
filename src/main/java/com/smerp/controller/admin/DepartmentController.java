package com.smerp.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smerp.model.admin.Department;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.admin.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	private static final Logger logger = LogManager.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	CompanyServices companyServices;

		@GetMapping(value = "/list")
		public String list(Model model) {
			logger.info("Inside DesignationController List Method");
			model.addAttribute("companyList", companyServices.findAll());
			model.addAttribute("departmentList",departmentService.findAll() );
			return "department/list";
		}
		
		@PostMapping(value = "/save")
		public String save(@ModelAttribute("department") Department department, BindingResult result) {
			logger.info("Inside DepartmentController Save Method");
			departmentService.save(department);
			return "redirect:list";
		}
		
		@GetMapping(value = "/create")
		public String create(Model model) {
			logger.info("Inside DepartmentController create Method");
			model.addAttribute("department", new Department());
			model.addAttribute("companyList", companyServices.findAll());
			model.addAttribute("departmentList", departmentService.findAll());
			return "department/create";
		}

		@GetMapping(value = "/getInfo")
		public String GetInfo(Model model, String departmentId) {
			logger.info("Inside DepartmentController GetInfo Method");
			model.addAttribute("department", departmentService.getInfo(Integer.parseInt(departmentId)));
			model.addAttribute("companyList", companyServices.getInfo(Integer.parseInt(departmentId)));
			return "department/create";
		}

		@PostMapping(value = "/delete")
		public String delete(String id) {
			logger.info("Inside DepartmentController Delete Method");
			departmentService.delete(Integer.parseInt(id));
			return "redirect:list";
		}
		
		@GetMapping(value = "/view")
		public String view(String departmentId, Model model) {
			logger.info("Inside DepartmentController view Method");
			model.addAttribute("departmentObj", departmentService.getInfo(Integer.parseInt(departmentId)));
			return "department/view";
		}
}
