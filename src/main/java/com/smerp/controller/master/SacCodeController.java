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
		List<SACCode> saccodeList = sacService.findAll();
		model.addAttribute("saccodeList", saccodeList);
		return "masters/saccode/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		model.addAttribute("saccode", new SACCode());
		return "masters/saccode/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String saccodeId) {
		SACCode saccodeObj = sacService.findById(Integer.parseInt(saccodeId));
		model.addAttribute("saccodeObj", saccodeObj);
		model.addAttribute("saccode", new SACCode());
		return "masters/saccode/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String saccodeId) {
		sacService.delete(Integer.parseInt(saccodeId));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("saccode") SACCode saccode, BindingResult result) {
		sacService.save(saccode);
		return "redirect:list";
	}
	@GetMapping(value = "/view")
	public String view(String saccodeId, Model model,HttpServletRequest request) {
		SACCode saccodeObj = sacService.getInfo(Integer.parseInt(saccodeId));
		model.addAttribute("saccodeObj", saccodeObj);  
		return "masters/saccode/view";
	}
}
