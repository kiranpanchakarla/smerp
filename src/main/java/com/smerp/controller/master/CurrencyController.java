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
import com.smerp.model.master.Currency;
import com.smerp.service.master.CurrencyServices;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

	private static final Logger logger = LogManager.getLogger(CurrencyController.class);
	@Autowired
	CurrencyServices currencyServices;

	@GetMapping(value = "/list")
	public String list(Model model) {
		logger.info("Inside CurrencyController List Method");
		model.addAttribute("currencyList", currencyServices.findAll());
		return "masters/currency/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		logger.info("Inside CurrencyController Create Method");
		model.addAttribute("currency", new Currency());
		return "masters/currency/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String currencyId) {
		logger.info("Inside CurrencyController GetInfo Method");
		model.addAttribute("currencyObj", currencyServices.findById(Integer.parseInt(currencyId)));
		model.addAttribute("currency", new Currency());
		return "masters/currency/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside CurrencyController delete Method");
		currencyServices.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("currency") Currency currency, BindingResult result) {
		logger.info("Inside CurrencyController save Method");
		currencyServices.save(currency);
		return "redirect:list";
	}

	@GetMapping(value = "/view")
	public String view(String currencyId, Model model) {
		logger.info("Inside CurrencyController view Method");
		model.addAttribute("currencyObj", currencyServices.getInfo(Integer.parseInt(currencyId)));
		return "masters/currency/view";
	}
}
