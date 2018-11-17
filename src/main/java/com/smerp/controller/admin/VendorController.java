package com.smerp.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.master.CountryServices;
@Configuration
@PropertySource("classpath:application.properties")

@Controller
@RequestMapping("/vendor")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	VendorsContactDetailsService vendorsContactDetailsService;
	
	@Autowired
	VendorAddressService	vendorAddressService;
	
	@Autowired
	CountryServices countryServices;
	
	private static String countryCode;

	@Value(value = "${country.code}")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	private static final Logger logger = LogManager.getLogger(VendorController.class);
	
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("vendorList", vendorService.findByIsActive());
		return "vendor/list";
	}
	
	
	@GetMapping(value = "/create")
	public String vendorCreate(Model model) {
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode))); // for india pass value code 1
		model.addAttribute("vendor", new Vendor());
		return "vendor/create";
	}
	
	
	
	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute("vendor") Vendor vendor, BindingResult bindingResult, Model model,BindingResult result ) {
		logger.info("vendor" +vendor);
		//vendorService.save(vendor);
		return "redirect:list";
	}
	
	@GetMapping(value = "/getInfo")
	public String getInfo(String vendorId, Model model, HttpServletRequest request) {
		logger.info("companyId" + vendorId);
		Vendor vendor = vendorService.getInfo(Integer.parseInt(vendorId));
		model.addAttribute("vendor", vendor);
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode))); //  for india pass value code 1
		return "vendor/create";
	}

	@GetMapping(value = "/view")
	public String view(String vendorId, Model model, HttpServletRequest request) {
		logger.info("Id" + vendorId);
		Vendor vendor = vendorService.getInfo(Integer.parseInt(vendorId));
		model.addAttribute("vendor", vendor);
		return "vendor/view";
	}
	
	@PostMapping(value = "/delete")
	public String delete(String id) {
		vendorService.delete(Integer.parseInt(id));
		return "redirect:list";
	}
	
	@GetMapping(value = "/isValidVendorName")
	@ResponseBody
	public boolean isValidVendorName(String name) {
		logger.info("VendorName" + name);
		Vendor vendor = vendorService.findByName(name);
		if (vendor != null) {
			logger.info("Vendor Name  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/getVendorInfo", method = RequestMethod.GET)
	@ResponseBody
	private String getInvoiceListByInvNumber(@RequestParam("vendorname") String vendorname) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(vendorService.findByName(vendorname));
		
	}
	
	@RequestMapping(value = "/getShippingAddressInfo", method = RequestMethod.GET)
	@ResponseBody
	private String getShippingAddressInfo(@RequestParam("shippingId") String shippingId) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(vendorAddressService.findById(Integer.parseInt(shippingId)));
		
	}
	
}