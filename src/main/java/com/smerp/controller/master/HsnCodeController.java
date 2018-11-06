package com.smerp.controller.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	HsnService hsnCodeService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<HSNCode> hsncodeList = hsnCodeService.findAll();
		model.addAttribute("hsncodeList", hsncodeList);
		return "masters/hsncode/list";
	}

	@GetMapping(value = "/create")
	public String create(Model model) {
		model.addAttribute("hsncode", new HSNCode());
		return "masters/hsncode/create";
	}

	@GetMapping(value = "/getInfo")
	public String GetInfo(Model model, String hsncodeId) {
		HSNCode hsncodeObj = hsnCodeService.findById(Integer.parseInt(hsncodeId));
		model.addAttribute("hsncodeObj", hsncodeObj);
		model.addAttribute("hsncode", new HSNCode());
		return "masters/hsncode/create";
	}

	@PostMapping(value = "/delete")
	public String delete(String hsncodeId) {
		hsnCodeService.delete(Integer.parseInt(hsncodeId));
		return "redirect:list";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("hsncode") HSNCode hsncode, BindingResult result) {
		hsnCodeService.save(hsncode);
		return "redirect:list";
	}
	@GetMapping(value = "/view")
	public String view(String hsncodeId, Model model,HttpServletRequest request) {
		HSNCode hsncodeObj = hsnCodeService.getInfo(Integer.parseInt(hsncodeId));
		model.addAttribute("hsncodeObj", hsncodeObj); 
		return "masters/hsncode/view";
	}
}
