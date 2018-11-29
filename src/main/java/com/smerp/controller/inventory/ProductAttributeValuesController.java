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
import com.smerp.model.inventory.ProductAttributeValues;
import com.smerp.service.inventory.ProductAttributeValuesService;
import com.smerp.service.inventory.ProductAttributesService;

@Controller
@RequestMapping("/productattributesvalues")
public class ProductAttributeValuesController {

private static final Logger logger = LogManager.getLogger(ProductAttributeValuesController.class);
	
	@Autowired
	ProductAttributeValuesService attributeValuesService;
	
	@Autowired
	ProductAttributesService productAttributesService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside ProductAttributesvaluesController List Method");
		model.addAttribute("productattributesvaluesList", attributeValuesService.findAll());
		return "inventory/productattributevalues/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside ProductAttributesvaluesController Create Method");
		model.addAttribute("productattributesvalues", new ProductAttributeValues());
		model.addAttribute("productAttributesList", productAttributesService.findAll());
		return "inventory/productattributevalues/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String productattributesvaluesId) {
		logger.info("Inside ProductAttributesvaluesController GetInfo Method");
		model.addAttribute("productattributesvalues", attributeValuesService.findById(Integer.parseInt(productattributesvaluesId)));
		
		model.addAttribute("productAttributesList", productAttributesService.findAll());
		return "inventory/productattributevalues/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside ProductAttributesvaluesController delete Method");
		attributeValuesService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("productattributesvalues") ProductAttributeValues productattributesvalues, BindingResult result) {
		logger.info("Inside ProductAttributesvaluesController save Method");
		attributeValuesService.save(productattributesvalues);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String productattributesvaluesId, Model model) {
		logger.info("Inside ProductAttributesvaluesController view Method");
		model.addAttribute("productattributesvaluesObj", attributeValuesService.getInfo(Integer.parseInt(productattributesvaluesId)));
		return "inventory/productattributevalues/view";
		
	}
}
