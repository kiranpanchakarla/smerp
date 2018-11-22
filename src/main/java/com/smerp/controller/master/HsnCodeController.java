package com.smerp.controller.master;

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
import com.smerp.model.master.HSNCode;
import com.smerp.service.master.HsnService;

@Controller
@RequestMapping("/hsncode")
public class HsnCodeController {

	private static final Logger logger = LogManager.getLogger(HsnCodeController.class);

	@Autowired
	HsnService hsnCodeService;

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside HsnCodeController List Method");
		model.addAttribute("hsncodeList", hsnCodeService.findAll());
		return "masters/hsncode/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside HsnCodeController Create Method");
		model.addAttribute("hsncode", new HSNCode());
		return "masters/hsncode/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String hsncodeId) {
		logger.info("Inside HsnCodeController GetInfo Method");
		model.addAttribute("hsncodeObj", hsnCodeService.findById(Integer.parseInt(hsncodeId)));
		model.addAttribute("hsncode", new HSNCode());
		return "masters/hsncode/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside HsnCodeController delete Method");
		hsnCodeService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("hsncode") HSNCode hsncode, BindingResult result) {
		logger.info("Inside HsnCodeController save Method");
		hsnCodeService.save(hsncode);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String hsncodeId, Model model) {
		logger.info("Inside HsnCodeController view Method");
		model.addAttribute("hsncodeObj", hsnCodeService.getInfo(Integer.parseInt(hsncodeId)));
		return "masters/hsncode/view";
	}
}
