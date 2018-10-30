package com.smerp.controller.master;

import java.util.List;

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

	@Autowired
	CurrencyServices currencyServices;

	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Currency> currencyList = currencyServices.findAll();
		model.addAttribute("currencyList", currencyList);
		return "masters/currency/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		model.addAttribute("currency", new Currency());
		return "masters/currency/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String currencyId) {
		Currency currencyObj = currencyServices.findById(Integer.parseInt(currencyId));
		model.addAttribute("currencyObj", currencyObj);
		model.addAttribute("currency", new Currency());
		return "masters/currency/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		currencyServices.delete(Integer.parseInt(id));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("currency") Currency currency, BindingResult result) {
		currencyServices.save(currency);
		return "redirect:list";
	}

}
