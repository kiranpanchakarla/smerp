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
import com.smerp.model.admin.Plant;
import com.smerp.service.master.PlantService;


@Controller
@RequestMapping("/plant")
public class PlantController {

	private static final Logger logger = LogManager.getLogger(PlantController.class);
	
	@Autowired
	PlantService plantService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside PlantController List Method");
		model.addAttribute("plantList", plantService.findAll());
		return "inventory/plant/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside PlantController Create Method");
		model.addAttribute("plant", new Plant());
		return "inventory/plant/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String plantId) {
		logger.info("Inside PlantController GetInfo Method");
		model.addAttribute("plant", plantService.findById(Integer.parseInt(plantId)));
		 
		return "inventory/plant/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside PlantController delete Method");
		plantService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("plant") Plant plant, BindingResult result) {
		logger.info("Inside PlantController save Method");
		plantService.save(plant);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String plantId, Model model) {
		logger.info("Inside PlantController view Method");
		model.addAttribute("plantObj", plantService.getInfo(Integer.parseInt(plantId)));
		return "inventory/plant/view";
	}
}
