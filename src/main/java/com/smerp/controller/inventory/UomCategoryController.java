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
import org.springframework.web.bind.annotation.ResponseBody;

import com.smerp.model.inventory.UomCategory;
import com.smerp.model.master.Currency;
import com.smerp.service.inventory.UomCatergoryService;

@Controller
@RequestMapping("/uomcategory")
public class UomCategoryController {

	private static final Logger logger = LogManager.getLogger(UomCategoryController.class);
	
	@Autowired
	UomCatergoryService catergoryService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside UomCategoryController List Method");
		model.addAttribute("uomcategoryList", catergoryService.findAll());
		return "inventory/uomcategory/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside UomCategoryController Create Method");
		model.addAttribute("uomcategory", new UomCategory());
		
		return "inventory/uomcategory/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String uomcategoryId) {
		logger.info("Inside UomCategoryController GetInfo Method");
		model.addAttribute("uomcategoryObj", catergoryService.findById(Integer.parseInt(uomcategoryId)));
		model.addAttribute("uomcategory", new UomCategory());
		return "inventory/uomcategory/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside UomCategoryController delete Method");
		catergoryService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("uomcategory") UomCategory uomcategory, BindingResult result) {
		logger.info("Inside UomCategoryController save Method");
		catergoryService.save(uomcategory);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String uomcategoryId, Model model) {
		logger.info("Inside UomCategoryController view Method");
		model.addAttribute("uomcategoryObj", catergoryService.getInfo(Integer.parseInt(uomcategoryId)));
		return "inventory/uomcategory/view";
	}
	
	@GetMapping(value = "/isValidUomCategoryName")
	@ResponseBody
	public boolean isValidUomCategoryName(String name) {
		logger.info("currencyName" + name);
		UomCategory uomcategory = catergoryService.findByName(name);
		if (uomcategory != null) {
			logger.info("UOM Category Name  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
}
