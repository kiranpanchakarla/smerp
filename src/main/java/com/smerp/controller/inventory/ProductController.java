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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.model.admin.ProductQuantity;
import com.smerp.model.inventory.Product;
import com.smerp.model.inventory.ProductType;
import com.smerp.model.inventory.Uom;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductCategoryService;
import com.smerp.service.inventory.ProductQuantityService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.ProductTypeService;
import com.smerp.service.inventory.UomCatergoryService;
import com.smerp.service.inventory.UomService;
import com.smerp.service.master.HsnService;
import com.smerp.service.master.SacService;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.ProductList;

@Controller
@RequestMapping("/product")
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
	
	@Autowired
	private VendorService vendorService;
	
	
	@Autowired
	ProductList productList;
	
	@Autowired
	ProductQuantityService productQuantityService;
	
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	
	@GetMapping(value = "/create")
	public String productCreate(Model model)  throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("uomCategoryList", uomCatergoryService.findAll());
		model.addAttribute("hsnList", hsnService.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productCategoryList", productCategoryService.findAll());
		model.addAttribute("productGroupList", productTypeService.findAll());
		model.addAttribute("manageProductByList", productList.getManageProductBy());
		model.addAttribute("productTaxCategoryList", productList.getProductTaxCategory());
		model.addAttribute("productTypeList", productList.getProductType());
		model.addAttribute("taxCategoryList", productList.getTaxCategory());
		model.addAttribute("valuationMethodList", productList.getValuationMethod());
	    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		
		
		model.addAttribute("product", new Product());
		return "product/create";
	}
	
	@PostMapping(value = "/save")
	public String save( Product product,Model model,BindingResult result) {
	    productService.save(product);
		return "redirect:list";
	}
		
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Product> productList = productService.findByIsActive();
		model.addAttribute("productList", productList);
		return "product/list";
	}
		
	@GetMapping(value = "/getInfo")
	public String getInfo(String productId, Model model) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Product product=productService.getInfo(Integer.parseInt(productId));
		model.addAttribute("product", productService.getInfo(Integer.parseInt(productId)));
		model.addAttribute("uomCategoryList", uomCatergoryService.findAll());
		model.addAttribute("hsnList", hsnService.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productGroupList", productTypeService.findAll());
		model.addAttribute("productTypeList", productTypeService.findAll());
		model.addAttribute("manageProductByList", productList.getManageProductBy());
		model.addAttribute("productTaxCategoryList", productList.getProductTaxCategory());
		model.addAttribute("productTypeList", productList.getProductType());
		model.addAttribute("taxCategoryList", productList.getTaxCategory());
		model.addAttribute("valuationMethodList", productList.getValuationMethod());
		model.addAttribute("valuationMethodList", productList.getValuationMethod());
		model.addAttribute("uomList", uomService.uomList(product.getUomCategory().getId()));
		 mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		return "product/create";
	}
	
	@GetMapping(value = "/view")
	public String view(String productId, Model model) {
		Product product=productService.getInfo(Integer.parseInt(productId));
		List<ProductQuantity> productQuantity = productQuantityService.findProductOrderedQuantity(product.getId());
		model.addAttribute("product",product);
		model.addAttribute("uomCategoryList", uomCatergoryService.findAll());
		model.addAttribute("hsnList", hsnService.findAll());
		model.addAttribute("sacList", sacService.findAll());
		model.addAttribute("productCategoryList", productCategoryService.findAll());
		model.addAttribute("productTypeList", productTypeService.findAll());
		model.addAttribute("manageProductByList", productList.getManageProductBy());
		model.addAttribute("productTaxCategoryList", productList.getProductTaxCategory());
		model.addAttribute("productTypeList", productList.getProductType());
		model.addAttribute("taxCategoryList", productList.getTaxCategory());
		model.addAttribute("uomList", uomService.uomList(product.getUomCategory().getId()));
		model.addAttribute("productQuantity", productQuantity);
		//model.addAttribute("uomList",  uomService.uomList();
		return "product/view";
	}
		
	@RequestMapping(value = "/getUOMList", method = RequestMethod.GET)	
	@ResponseBody
	public Map<Integer, Object> getUomList(@RequestParam("id") String id) {
		logger.info("Inside getUomList method " + id);
		List<Uom> list = uomService.uomList(Integer.parseInt(id));
		Map<Integer, Object> map = list.stream()
				.collect(Collectors.toMap(Uom::getId, Uom::getUomName));
		logger.info("length  " + list.size());
		return map;
	}
		
	@GetMapping(value = "/isValidProductNo")
	@ResponseBody
	public boolean isValidProductNo(String name) {
		logger.info("companyName" + name);
		Product product = productService.findByDescription(name);
		if (product != null) {
			logger.info("Product Description  Already Exits!");
			return true;
		} else {
			return false;
		}
	}
    	
	
	@GetMapping(value = "/setProductNo")
	@ResponseBody
	public String  setProductNo(String productGroup) {
		logger.info("productGroup" + productGroup);
		Product product = productService.findLastCodeNumber(productGroup);
		if(product == null) {
			ProductType type = productTypeService.findByName(productGroup);
			productGroup =  type.getProductName();
		}
		return GenerateDocNumber.autoGenereater(""+EnumStatusUpdate.PGP, product == null ? productGroup :  product.getProductNo());
	}
	
	@PostMapping(value = "/delete")
	public String deleteProduct(@RequestParam("id") int id) {
		
		logger.info("Delete msg");
		productService.delete(id);
		return "redirect:list";
	}
	
	
	
	@RequestMapping(value = "/getProductInfo", method = RequestMethod.GET)
    @ResponseBody
    private String getInvoiceListByProductNumber(@RequestParam("name") String name) throws JsonProcessingException {
		Product product = productService.findByproductNo(name);
       // logger.info("product Obj-->" + product );
        if(product!=null) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper.writeValueAsString(product);
        }else {
        	return "";
        }
    }
	
	@RequestMapping(value = "/getProductInfoByDescription", method = RequestMethod.GET)
    @ResponseBody
    private String getInvoiceListByDescription(@RequestParam("name") String name) throws JsonProcessingException {
        Product product = productService.findByDescription(name);
       // logger.info("product Obj-->" + product );
        if(product!=null) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
       // logger.info(mapper.writeValueAsString(product));
        return mapper.writeValueAsString(product);
        }else {
        	return "";
        }
    }

}
