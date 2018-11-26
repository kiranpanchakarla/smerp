package com.smerp.controller.inventory;

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

import com.smerp.model.inventory.Uom;
import com.smerp.model.inventory.UomCategory;
import com.smerp.service.inventory.UomCatergoryService;
import com.smerp.service.inventory.UomService;

@Controller
@RequestMapping("/uom")
public class UomController {

private static final Logger logger = LogManager.getLogger(UomController.class);
	
	@Autowired
	UomService uomService;
	
	@Autowired
	UomCatergoryService catergoryService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside UomController List Method");
		model.addAttribute("uomList", uomService.findAll());
		model.addAttribute("uomCategoryList", catergoryService.findAll());
		return "inventory/uom/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside UomController Create Method");
		model.addAttribute("uom", new Uom());
		model.addAttribute("uomCategoryList", catergoryService.findAll());
		return "inventory/uom/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String uomId) {
		logger.info("Inside UomController GetInfo Method");
		model.addAttribute("uom", uomService.findById(Integer.parseInt(uomId)));
		model.addAttribute("uomCategoryList", catergoryService.findAll());
		return "inventory/uom/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside UomController delete Method");
		uomService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("uom") Uom uom, BindingResult result) {
		logger.info("Inside UomCategoryController save Method");
		uomService.save(uom);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String uomId, Model model) {
		logger.info("Inside UomController view Method");
		model.addAttribute("uomObj", uomService.getInfo(Integer.parseInt(uomId)));
		return "inventory/uom/view";
	}
}
