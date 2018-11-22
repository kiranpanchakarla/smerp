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
import com.smerp.model.master.TimeZone;
import com.smerp.service.master.CountryServices;
import com.smerp.service.master.TimeZoneService;

@Controller
@RequestMapping("/timezone")
public class TimeZoneController {

	private static final Logger logger = LogManager.getLogger(TimeZoneController.class);

	@Autowired
	TimeZoneService timeZoneService;

	@Autowired
	CountryServices countryService;

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside TimeZoneController List Method");
		model.addAttribute("timezoneList", timeZoneService.findAll());
		return "masters/timezone/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside TimeZoneController create Method");
		model.addAttribute("timezone", new TimeZone());
		model.addAttribute("countryList", countryService.countryList());
		return "masters/timezone/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String timezoneId) {
		logger.info("Inside TimeZoneController GetInfo Method");
		model.addAttribute("timezoneObj", timeZoneService.findById(Integer.parseInt(timezoneId)));
		model.addAttribute("timezone", new TimeZone());
		model.addAttribute("countryList", countryService.countryList());
		return "masters/timezone/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside TimeZoneController delete Method");
		timeZoneService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("timezone") TimeZone timezone, BindingResult result) {
		logger.info("Inside TimeZoneController save Method");
		timeZoneService.save(timezone);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String timezoneId, Model model) {
		logger.info("Inside TimeZoneController view Method");
		model.addAttribute("timezoneObj", timeZoneService.getInfo(Integer.parseInt(timezoneId)));
		return "masters/timezone/view";
	}
}
