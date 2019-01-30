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

import com.smerp.model.admin.Vendor;
import com.smerp.model.inventory.ProductType;
import com.smerp.model.inventory.UomCategory;
import com.smerp.service.inventory.ProductTypeService;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;

@Controller
@RequestMapping("/producttype")
public class ProductTypeController {

	private static final Logger logger = LogManager.getLogger(ProductTypeController.class);
	@Autowired
	ProductTypeService productTypeService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside ProductTypeController List Method");
		model.addAttribute("producttypeList", productTypeService.findAll());
		return "inventory/producttype/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside ProductTypeController Create Method");
		
		ProductType productType = productTypeService.findLastCodeNumber();
		ProductType productTypeObj =  new ProductType();
		productTypeObj.setProductName(GenerateDocNumber.autoGenereater(""+EnumStatusUpdate.PG, productType == null ? "" :  productType.getProductName()));
		
		model.addAttribute("producttype", productTypeObj);
		return "inventory/producttype/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String producttypeId) {
		logger.info("Inside ProductTypeController GetInfo Method");
		model.addAttribute("producttypeObj", productTypeService.findbyId(Integer.parseInt(producttypeId)));
		model.addAttribute("producttype", new ProductType());
		return "inventory/producttype/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside ProductTypeController delete Method");
		productTypeService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("producttype") ProductType producttype, BindingResult result) {
		logger.info("Inside ProductTypeController save Method");
		productTypeService.save(producttype);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String producttypeId, Model model) {
		logger.info("Inside ProductTypeController view Method");
		model.addAttribute("producttypeObj", productTypeService.getInfo(Integer.parseInt(producttypeId)));
		return "inventory/producttype/view";
	}
	
	@GetMapping(value = "/isValidProductGroupDescription")
	@ResponseBody
	public boolean isValidProductGroupDescription(String name) {
		ProductType productType = productTypeService.findByName(name);
		if (productType != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
