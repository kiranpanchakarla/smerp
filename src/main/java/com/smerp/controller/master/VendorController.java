package com.smerp.controller.master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smerp.model.admin.Vendor;
import com.smerp.service.admin.VendorService;

@Controller
@RequestMapping("/vendor")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	
	private static final Logger logger = LogManager.getLogger(VendorController.class);
	
	
	@GetMapping(value = "/create")
	public String vendorCreate(Model model) {
		
		model.addAttribute("vendor", new Vendor());
		return "vendor/create";
	}
	
	
	
	@PostMapping(value = "/save")
	public String save( Vendor vendor,Model model,BindingResult result) {
		/*logger.info(product);
		productService.save(product);*/
		return   null;
	}

}
