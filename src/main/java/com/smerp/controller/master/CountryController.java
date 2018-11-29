package com.smerp.controller.master;

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

import com.smerp.model.admin.Company;
import com.smerp.model.master.Country;
import com.smerp.service.master.CountryServices;
import com.smerp.service.master.CurrencyServices;

@Controller
@RequestMapping("/country")
public class CountryController {

	private static final Logger logger = LogManager.getLogger(CountryController.class);

	@Autowired
	CountryServices countryServices;

	@Autowired
	CurrencyServices currencyServices;
	
	

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside CountryController List Method");
		model.addAttribute("countryList", countryServices.countryList());
		return "masters/country/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside CountryController create Method");
		model.addAttribute("country", new Country());
		model.addAttribute("currencyList", currencyServices.findAll());
		return "masters/country/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String countryId) {
		logger.info("Inside CountryController GetInfo Method");
		model.addAttribute("country", countryServices.getInfo(Integer.parseInt(countryId)));
		//model.addAttribute("country", new Country());
		model.addAttribute("currencyList", currencyServices.findAll());
		return "masters/country/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside CountryController Delete Method");
		countryServices.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("country") Country country, BindingResult result) {
		logger.info("Inside CountryController Save Method");
		countryServices.save(country);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String countryId, Model model) {
		logger.info("Inside CountryController view Method");
		model.addAttribute("countryObj", countryServices.getInfo(Integer.parseInt(countryId)));
		return "masters/country/view";
	}
	
	@GetMapping(value = "/isValidCountryName")
	@ResponseBody
	public boolean isValidCountryName(String name) {
		logger.info("countryName" + name);
		Country country = countryServices.findByName(name);
		if (country != null) {
			logger.info("Company Name  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
}
