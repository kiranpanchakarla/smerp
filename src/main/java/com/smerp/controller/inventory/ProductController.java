package com.smerp.controller.inventory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smerp.model.inventory.Product;
import com.smerp.model.inventory.Uom;
import com.smerp.service.inventory.ProductCategoryService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.ProductTypeService;
import com.smerp.service.inventory.UomCatergoryService;
import com.smerp.service.inventory.UomService;
import com.smerp.service.master.HsnService;
import com.smerp.service.master.SacService;

@Controller
@RequestMapping("/inventory")
public class ProductController {
	
	
	@Autowired
	ProductService  productService;
	
	@Autowired
	UomService  uomService;
	
	
	@Autowired
    UomCatergoryService uomCatergoryService;
	
	@Autowired
	HsnService hsnService;
	
	
	@Autowired
	SacService  sacService;	 
	
	@Autowired
	ProductCategoryService productCategoryService; 
	
	@Autowired
	ProductTypeService productTypeService;
	
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	
	@GetMapping(value = "/create")
	public String productCreate(Model model) {
		
		logger.info("create");
		model.addAttribute("uomCategoryList", uomCatergoryService.findAll());
		//model.addAttribute("uomList", uomService.findAll());
		model.addAttribute("hsnList", hsnService.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productCategoryList", productCategoryService.findAll());
		model.addAttribute("productTypeList", productTypeService.findAll());
		logger.info("uomCategoryList");
		logger.info("uomList");
		model.addAttribute("product", new Product());
		return "inventory/create";
	}
	

	@PostMapping(value = "/save")
	public String save( Product product,Model model,BindingResult result) {
		logger.info(product);
		productService.save(product);
		return "redirect:productList";
	}
	
	
	/*@RequestMapping(value = "/details", method = RequestMethod.GET)
    @ResponseBody
    public List<Uom> uomDeatils(@RequestParam("id") Integer id) {
		
		List<Uom>  uomList = uomService.findById(id);
        return uomList;
    }*/
	
	
	@GetMapping(value = "/productList")
	public String list(Model model) {
		//List<Product> productList = productService.findByIsActive(true);
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		return "inventory/productList";
	}
	
	
	/*@PostMapping(value = "/delete")
	public String deleteProduct(String id) {
		productService.delete(Integer.parseInt(id)); delete
		return "redirect:list";
	}*/
	
	
	@GetMapping(value = "/getInfo")
	public String getInfo(String productId, Model model) {
		model.addAttribute("product", productService.getInfo(Integer.parseInt(productId)));
		model.addAttribute("uomCategoryList", uomCatergoryService.findAll());
		//model.addAttribute("uomList", uomService.findAll());
		model.addAttribute("hsnList", hsnService.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productCategoryList", productCategoryService.findAll());
		model.addAttribute("productTypeList", productTypeService.findAll());
		return "inventory/create";
	}
	
	@GetMapping(value = "/view")
	public String view(String productId, Model model) {
		model.addAttribute("product", productService.getInfo(Integer.parseInt(productId)));
		model.addAttribute("uomCategoryList", uomCatergoryService.findAll());
		//model.addAttribute("uomList", uomService.findAll());
		model.addAttribute("hsnList", hsnService.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productCategoryList", productCategoryService.findAll());
		model.addAttribute("productTypeList", productTypeService.findAll());
		return "inventory/view";
	}
	
	
	@RequestMapping(value = "/getUOMList", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, Object> getDesignationsList(@RequestParam("id") String id) {
		logger.info("Inside getDesignationsList method " + id);
		List<Uom> list = uomService.uomList(Integer.parseInt(id));
		Map<Integer, Object> map = list.stream()
				.collect(Collectors.toMap(Uom::getId, Uom::getUomName));
		logger.info("length  " + list.size());
		return map;
	}
	
	
	
	
	@PostMapping(value = "/delete")
	public String deleteProduct(@RequestParam("id") int id) {
		
		logger.info("Delete msg");
		productService.delete(id);
		return "redirect:productList";
	}

}
