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

import com.smerp.model.inventory.ProductAttributes;
import com.smerp.model.inventory.ProductType;
import com.smerp.service.inventory.ProductAttributesService;

@Controller
@RequestMapping("/productattributes")
public class ProductAttributesController {

	private static final Logger logger = LogManager.getLogger(ProductAttributesController.class);
	
	@Autowired
	ProductAttributesService attributesService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside ProductAttributesController List Method");
		model.addAttribute("productattributesList", attributesService.findAll());
		return "inventory/productattributes/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside ProductAttributesController Create Method");
		model.addAttribute("productattributes", new ProductAttributes());
		return "inventory/productattributes/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String productattributesId) {
		logger.info("Inside ProductAttributesController GetInfo Method");
		model.addAttribute("productattributesObj", attributesService.findById(Integer.parseInt(productattributesId)));
		model.addAttribute("productattributes", new ProductType());
		return "inventory/productattributes/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside ProductAttributesController delete Method");
		attributesService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("productattributes") ProductAttributes productattributes, BindingResult result) {
		logger.info("Inside ProductAttributesController save Method");
		attributesService.save(productattributes);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String productattributesId, Model model) {
		logger.info("Inside ProductAttributesController view Method");
		model.addAttribute("productattributesObj", attributesService.getInfo(Integer.parseInt(productattributesId)));
		return "inventory/productattributes/view";
	}
}
