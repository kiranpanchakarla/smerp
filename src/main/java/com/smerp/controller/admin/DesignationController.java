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
import org.springframework.web.bind.annotation.ResponseBody;

import com.smerp.model.admin.Desigination;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.admin.DesignationService;

@Controller
@RequestMapping("/designation")
public class DesignationController {

	private static final Logger logger = LogManager.getLogger(DesignationController.class);

	@Autowired
	DesignationService designationService;

	@Autowired
	CompanyServices companyServices;

	@Autowired
	DepartmentService departmentService;

	/*
	 * public void list() {
	 * 
	 * List<Designation> list=designationService.findAll();
	 * 
	 * }
	 */

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside DesignationController List Method");
		model.addAttribute("designationList", designationService.findAll());
		model.addAttribute("companyList", companyServices.findByIsActive());
		model.addAttribute("departmentList", departmentService.findAll());
		return "designation/list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("designation") Desigination designation, BindingResult result) {
		logger.info("Inside DesignationController Save Method");
		designationService.save(designation);
		return "redirect:list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside DesignationController create Method");
		model.addAttribute("designation", new Desigination());
		model.addAttribute("companyList", companyServices.findByIsActive());
		model.addAttribute("departmentList", departmentService.findAll());
		return "designation/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String designationId) {
		logger.info("Inside DesignationController GetInfo Method");
		model.addAttribute("designation", designationService.getInfo(Integer.parseInt(designationId)));
		model.addAttribute("companyList", companyServices.findByIsActive());
		model.addAttribute("departmentList", departmentService.findAll());
		return "designation/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside DesignationController Delete Method");
		designationService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String designationId, Model model) {
		logger.info("Inside DesignationController view Method");
		model.addAttribute("designationObj", designationService.getInfo(Integer.parseInt(designationId)));
		return "designation/view";
	}

	@GetMapping(value = "/isValidDesiginationName")
	@ResponseBody
	public boolean isValidDepartmentName(String name, String departmentId) {
		logger.info("Desigination Name" + name);
		logger.info("ID" + departmentId);
		Integer deptId = Integer.parseInt(departmentId);

		boolean valid = designationService.isValid(name, deptId);

		return valid;

		/*
		 * Desigination designation = designationService.findByName(name); if
		 * (designation != null) { logger.info("Department Name  Already Exits!");
		 * return true; } else { return false; }
		 * 
		 * }
		 */
	}
}
