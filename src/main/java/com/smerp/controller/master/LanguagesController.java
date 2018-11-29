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
import org.springframework.web.bind.annotation.ResponseBody;

import com.smerp.model.master.Currency;
import com.smerp.model.master.Languages;
import com.smerp.service.master.LanguagesService;

@Controller
@RequestMapping("/languages")
public class LanguagesController {

	private static final Logger logger = LogManager.getLogger(LanguagesController.class);

	@Autowired
	LanguagesService languagesService;

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside LanguagesController List Method");
		model.addAttribute("languagesList", languagesService.findAll());
		return "masters/languages/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside LanguagesController create Method");
		model.addAttribute("languages", new Languages());
		return "masters/languages/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String languagesId) {
		logger.info("Inside LanguagesController GetInfo Method");
		model.addAttribute("languagesObj", languagesService.findById(Integer.parseInt(languagesId)));
		model.addAttribute("languages", new Languages());
		return "masters/languages/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside LanguagesController delete Method");
		languagesService.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("languages") Languages languages, BindingResult result) {
		logger.info("Inside LanguagesController save Method");
		languagesService.save(languages);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String languagesId, Model model) {
		logger.info("Inside LanguagesController view Method");
		model.addAttribute("languagesObj", languagesService.getInfo(Integer.parseInt(languagesId)));
		return "masters/languages/view";
	}
	
	@GetMapping(value = "/isValidLanguageName")
	@ResponseBody
	public boolean isValidLanguageName(String name) {
		logger.info("languagesName" + name);
		Languages languages = languagesService.findByName(name);
		if (languages != null) {
			logger.info("Languages Name  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
}
