package com.smerp.controller.inventory;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smerp.controller.admin.CompanyController;
import com.smerp.model.inventory.Product;
import com.smerp.service.inventory.ProductCategoryService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.ProductTypeService;
import com.smerp.service.inventory.UomCatergoryService;
import com.smerp.service.inventory.UomService;
import com.smerp.service.master.HsnSevice;
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
	HsnSevice HsnSevice;
	
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
		model.addAttribute("uomList", uomService.findAll());
		model.addAttribute("hsnList", HsnSevice.findAll());
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
		List<Product> productList = productService.findByIsActive(true);
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
		model.addAttribute("uomList", uomService.findAll());
		model.addAttribute("hsnList", HsnSevice.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productCategoryList", productCategoryService.findAll());
		model.addAttribute("productTypeList", productTypeService.findAll());
		return "inventory/create";
	}
	
	
	@PostMapping(value = "/delete")
	public String deleteProduct(@RequestParam("id") int id) {
		
		logger.info("Delete msg");
		productService.delete(id);
		return "redirect:list";
	}

}
