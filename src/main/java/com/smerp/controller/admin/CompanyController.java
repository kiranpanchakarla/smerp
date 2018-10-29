package com.smerp.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smerp.model.admin.Company;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.master.CountryServices;
@Controller

@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CountryServices countryServices;
	
	@Autowired
	CompanyServices companyServices;
	

	
	private static final Logger logger = LogManager.getLogger(CompanyController.class);

	@GetMapping(value = "/create")
	public String list(Model model) {
		model.addAttribute("countryList", countryServices.countryList());
		model.addAttribute("stateList", countryServices.stateList(1));
		model.addAttribute("company", new Company());
		return "company/create";
	}
	
	@PostMapping(value = "/save")
	public String save( Company company,Model model,BindingResult result) {
		company.setCurrency(company.getCountry().getCurrency());
		logger.info(company);
		//companyServices.save(company);
		model.addAttribute("countryList", countryServices.countryList());
		model.addAttribute("stateList", countryServices.stateList(1));
		model.addAttribute("company", new Company());
		return "company/create";
	}
	
		
	
}
