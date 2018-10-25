package com.smerp.controller.master;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smerp.model.admin.Company;
@Controller

@RequestMapping("/company")
public class CompanyController {

	@GetMapping(value = "/create")
	public String list(Model model) {
		model.addAttribute("company", new Company());
		return "company/create";
	}
	
}
