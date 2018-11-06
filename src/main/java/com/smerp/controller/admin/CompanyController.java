package com.smerp.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smerp.jwt.models.Constants;
import com.smerp.model.admin.Company;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.master.CountryServices;
import com.smerp.util.ContextUtil;
import com.smerp.util.FilePathUtil;

@Configuration
@PropertySource("classpath:application.properties")

@Controller
@RequestMapping("/company")
public class CompanyController {

	// Save the uploaded file to this folder

	private static String logoUploadedPath;

	@Value(value = "${file.upload.path}")
	public void setProp(String prop) {
		this.logoUploadedPath = prop;
	}

	private static String countryCode;

	@Value(value = "${country.code}")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Autowired
	CountryServices countryServices;

	@Autowired
	CompanyServices companyServices;

	private static final Logger logger = LogManager.getLogger(CompanyController.class);

	@GetMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("companyList", companyServices.findByIsActive());
		return "company/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode))); // for india pass value code 1
		model.addAttribute("stateList", countryServices.stateList(Integer.parseInt(countryCode))); //  for india pass value code 1
		model.addAttribute("company", new Company());
		return "company/create";
	}

	@GetMapping(value = "/getInfo")
	public String getInfo(String companyId, Model model, HttpServletRequest request) {
		logger.info("companyId" + companyId);
		Company company = companyServices.getInfo(Integer.parseInt(companyId));
		model.addAttribute("company", company);
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode))); //  for india pass value code 1
		model.addAttribute("stateList", countryServices.stateList(Integer.parseInt(countryCode))); //  for india pass value code 1
		if (company.getLogo() != null && !company.getLogo().equals("")) {
			model.addAttribute("filePath", ContextUtil.populateContext(request) + "/" + company.getLogo());
		} else {
			model.addAttribute("filePath", null);
		}
		return "company/create";
	}

	@GetMapping(value = "/view")
	public String view(String companyId, Model model, HttpServletRequest request) {
		logger.info("companyId" + companyId);
		Company company = companyServices.getInfo(Integer.parseInt(companyId));
		model.addAttribute("company", company);
		model.addAttribute("country", countryServices.findById(Integer.parseInt(countryCode))); //  for india pass value code 1
		model.addAttribute("stateList", countryServices.stateList(Integer.parseInt(countryCode))); //  for india pass value code 1
		if (company.getLogo() != null && !company.getLogo().equals("")) {
			model.addAttribute("filePath", ContextUtil.populateContext(request) + "/" + company.getLogo());
		} else {
			model.addAttribute("filePath", null);
		}

		return "company/view";
	}

	@GetMapping(value = "/isValidCompanyName")
	@ResponseBody
	public boolean isValidCompanyName(String name) {
		logger.info("companyName" + name);
		Company company = companyServices.findByName(name);
		if (company != null) {
			logger.info("Company Name  Already Exits!");
			return true;
		} else {
			return false;
		}
	}

	@GetMapping(value = "/isValidEmailId")
	@ResponseBody
	public boolean isValidEmailId(String name) {
		logger.info("companyeamilId" + name);
		Company company = companyServices.findByEmailId(name);
		if (company != null) {
			logger.info("Email  Already Exits!");
			return true;
		} else {
			return false;
		}
	}

	@PostMapping(value = "/save")
	public String save(@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			Company company, Model model, BindingResult result) throws IOException {
		logger.info("save Company--> ");
		company.setCurrency(company.getCountry().getCurrency());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
			Map<String, String> path = FilePathUtil.getFilePath(file, logoUploadedPath, Constants.COMPANYFOLDER);
			String pathToSave = path.get("pathToSave");
			String fullPath = path.get("fullPath");
			logger.info("fullPath--> " + fullPath);
			logger.info("pathToSave-->" + pathToSave);

			FilePathUtil.saveFile(file, fullPath);
			company.setLogo(pathToSave);
		}
		companyServices.save(company);
		return "redirect:list";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		companyServices.delete(Integer.parseInt(id));
		return "redirect:list";
	}

}
