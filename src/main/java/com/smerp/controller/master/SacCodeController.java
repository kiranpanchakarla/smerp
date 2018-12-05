package com.smerp.controller.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smerp.model.master.HSNCode;
import com.smerp.model.master.SACCode;
import com.smerp.service.master.SacService;

@Controller
@RequestMapping("/saccode")
public class SacCodeController {

	@Autowired
	SacService sacService;

	private static final Logger logger = LogManager.getLogger(SacCodeController.class);

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside SacCodeController List Method");
		List<SACCode> saccodeList = sacService.findAll();
		model.addAttribute("saccodeList", saccodeList);
		return "masters/saccode/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside SacCodeController create Method");
		model.addAttribute("saccode", new SACCode());
		return "masters/saccode/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String saccodeId) {
		logger.info("Inside SacCodeController GetInfo Method");
		SACCode saccode = sacService.findById(Integer.parseInt(saccodeId));
		model.addAttribute("saccode", new SACCode());
		return "masters/saccode/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside SacCodeController delete Method");
		sacService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("saccode") SACCode saccode, BindingResult result) {
		logger.info("Inside SacCodeController save Method");
		sacService.save(saccode);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String saccodeId, Model model, HttpServletRequest request) {
		logger.info("Inside SacCodeController view Method");
		SACCode saccodeObj = sacService.getInfo(Integer.parseInt(saccodeId));
		model.addAttribute("saccodeObj", saccodeObj);
		return "masters/saccode/view";
	}
	
	@RequestMapping(value = "/getSacInfo", method = RequestMethod.GET)
    @ResponseBody
    private String getSacDetailsBySacCode(@RequestParam("sacCode") String sacCode) throws JsonProcessingException {
        logger.info("sacCode"+ sacCode);
        return new ObjectMapper().writeValueAsString(sacService.findBySacCode(sacCode));
        
    }
	
	@GetMapping(value = "/isValidSACCode")
	@ResponseBody
	public boolean isValidSACCode(String name) {
		logger.info("sacCode" + name);
		SACCode saccode = sacService.findByCode(name);
		if (saccode != null) {
			logger.info("SAC Code Already Exits!");
			return true;
		} else {
			return false;
		}
	}
	
}
