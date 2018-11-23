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

import com.smerp.model.inventory.ProductType;
import com.smerp.model.master.Currency;
import com.smerp.service.inventory.ProductTypeService;

@Controller
@RequestMapping("/producttype")
public class ProductTypeController {

	private static final Logger logger = LogManager.getLogger(ProductTypeController.class);
	@Autowired
	ProductTypeService productTypeService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside CurrencyController List Method");
		model.addAttribute("producttypeList", productTypeService.findAll());
		return "inventory/producttype/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside CurrencyController Create Method");
		model.addAttribute("producttype", new ProductType());
		return "inventory/producttype/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String producttypeId) {
		logger.info("Inside CurrencyController GetInfo Method");
		model.addAttribute("producttypeObj", productTypeService.findbyId(Integer.parseInt(producttypeId)));
		model.addAttribute("producttype", new ProductType());
		return "inventory/producttype/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside CurrencyController delete Method");
		productTypeService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("producttype") ProductType producttype, BindingResult result) {
		logger.info("Inside CurrencyController save Method");
		productTypeService.save(producttype);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String producttypeId, Model model) {
		logger.info("Inside CurrencyController view Method");
		model.addAttribute("producttypeObj", productTypeService.getInfo(Integer.parseInt(producttypeId)));
		return "inventory/producttype/view";
	}
}
