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

import com.smerp.model.master.Currency;
import com.smerp.model.master.States;
import com.smerp.service.master.CountryServices;
import com.smerp.service.master.StatesService;

@Controller
@RequestMapping("/states")
public class StatesController {

	private static final Logger logger = LogManager.getLogger(StatesController.class);

	@Autowired
	StatesService statesService;

	@Autowired
	CountryServices countryService;

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside StatesController List Method");
		model.addAttribute("statesList", statesService.findAll());
		return "masters/states/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside StatesController create Method");
		model.addAttribute("states", new States());
		model.addAttribute("countryList", countryService.countryList());
		return "masters/states/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String statesId) {
		logger.info("Inside StatesController GetInfo Method");
		model.addAttribute("states", statesService.findById(Integer.parseInt(statesId)));
		model.addAttribute("countryList", countryService.countryList());
		return "masters/states/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside StatesController delete Method");
		statesService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("states") States states, BindingResult result) {
		logger.info("Inside StatesController save Method");
		statesService.save(states);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String statesId, Model model) {
		logger.info("Inside StatesController view Method");
		model.addAttribute("statesObj", statesService.getInfo(Integer.parseInt(statesId)));
		return "masters/states/view";
	}

	@GetMapping(value = "/isValidStateName")
	@ResponseBody
	public boolean isValidStateName(String name) {
		logger.info("statesName" + name);
		States states = statesService.findByName(name);
		if (states != null) {
			logger.info("States Name  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping(value = "/isValidStateCode")
	@ResponseBody
	public boolean isValidStateCode(String name) {
		logger.info("statesCode" + name);
		States states = statesService.findByCode(name);
		if (states != null) {
			logger.info("States Code  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
}
