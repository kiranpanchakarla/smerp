package com.smerp.controller.admin;

import java.util.Map;
import java.util.stream.Collectors;

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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.model.admin.Vendor;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.master.States;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.master.CountryServices;
import com.smerp.service.master.StatesService;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
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
	
	@Autowired
	StatesService statesService;
	
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
		Vendor vendor = vendorService.findLastCodeNumber();
		Vendor vendorObj =  new Vendor();
		vendorObj.setVendorCode(GenerateDocNumber.autoGenereater(""+EnumStatusUpdate.V, vendor == null ? "" :  vendor.getVendorCode()));
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode)));
		model.addAttribute("stateList", countryServices.stateList(Integer.parseInt(countryCode)));
		model.addAttribute("stateMap", stateMap());
		model.addAttribute("vendor", vendorObj);
		return "vendor/create";
	}
	
	
	
	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute("vendor") Vendor vendor, BindingResult bindingResult, Model model,BindingResult result ) {
		vendorService.save(vendor);
		return "redirect:list";
	}
	
	@GetMapping(value = "/getInfo")
	public String getInfo(String vendorId, Model model, HttpServletRequest request) {
		logger.info("companyId" + vendorId);
		Vendor vendor = vendorService.getInfo(Integer.parseInt(vendorId));
		model.addAttribute("vendor", vendor);
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode))); //  for india pass value code 1
		model.addAttribute("stateList", countryServices.stateList(Integer.parseInt(countryCode)));
		model.addAttribute("stateMap", stateMap());
		return "vendor/create";
	}

	@GetMapping(value = "/view")
	public String view(String vendorId, Model model, HttpServletRequest request) {
		logger.info("Id" + vendorId);
		Vendor vendor = vendorService.getInfo(Integer.parseInt(vendorId));
		model.addAttribute("totalAmt", vendorService.getTotalAmt(Integer.parseInt(vendorId)));
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
	
	@GetMapping(value = "/isValidVendorCode")
	@ResponseBody
	public boolean isValidVendorCode(String name) {
		logger.info("code---->" + name);
		Vendor vendor = vendorService.findByCode(name);
		if (vendor != null) {
			logger.info("Vendor Code  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/getVendorInfo", method = RequestMethod.GET)
	@ResponseBody
	private String getInvoiceListByInvNumber(@RequestParam("vendorname") String vendorname) throws JsonProcessingException {
		 ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return  mapper.writeValueAsString(vendorService.findByName(vendorname));
		
	}
	
	@RequestMapping(value = "/getShippingAddressInfo", method = RequestMethod.GET)
	@ResponseBody
	private String getShippingAddressInfo(@RequestParam("shippingId") String shippingId) throws JsonProcessingException {
		 ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return mapper.writeValueAsString(vendorAddressService.findById(Integer.parseInt(shippingId)));
		
	}
	
	
public Map<Integer,Object> stateMap() {
		return statesService.findAll().stream().collect(Collectors.toMap(States::getId, States::getName));
		 
	}

	
}
