package com.smerp.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smerp.model.admin.UPM;
import com.smerp.model.admin.User;
import com.smerp.service.UserService;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.admin.DashboardCountService;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.UPMService;
import com.smerp.service.purchase.PurchaseRequestService;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

/*	@Autowired
	TokenProvider tokenProvider;*/

	@Autowired
	private UserService userService;

	@Autowired
	CompanyServices companyServices;

	@Autowired
	ProductService productService;

	@Autowired
	VendorService vendorService;

	@Autowired
	UPMService uPMService;
	
	@Autowired
	PurchaseRequestService purchaseRequestService;
	
	@Autowired
	DashboardCountService dashboardCountService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletResponse response,HttpSession session)
			throws IOException {

		logger.error("Invalid username and password!");

		if (response != null) {
			logger.error("Invalid username and password!" + response.getStatus());
		}

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("errorMsg", "Invalid username or password");
			logger.error("Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("errorMsg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		//session.removeAttribute("umpmap");
		//session.invalidate();
		return model;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpSession session, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// String token=tokenProvider.generateToken(auth);

		// session.setAttribute("tokenId", token);

		try {
			User user = (User) auth.getPrincipal();
			Map<String, List<String>> map = usermodulepermissionsbyuserId(user, model);
			logger.info(" permissions details map" + map.size());
			session.setAttribute("umpmap", map);
			 
			logger.info("company details----------" + user.getCompany());
			logger.info("userService" + userService.findAll().size());
			logger.info("productService" + productService.findByIsActive().size());
			model.addAttribute("userListCount", userService.findByIsActive().size());
			model.addAttribute("productsCount", productService.findByIsActive().size());
			model.addAttribute("vendorListCount", vendorService.findByIsActive().size());
			model.addAttribute("companyListCount", companyServices.findByIsActive().size());
			model.addAttribute("dashboardCount", dashboardCountService.findAll());
			model.addAttribute("rfqCount", dashboardCountService.findRFQCount());
			model.addAttribute("poCount", dashboardCountService.findPOCount());
			model.addAttribute("grCount", dashboardCountService.findGoodsReceiptCount());
			//model.addAttribute("proCount", dashboardCountService.minProductQtyList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home";
	}

	@GetMapping("/purchase")
	public String getPurchase() {
		return "purchasehome";
	}
	private Map<String, List<String>> usermodulepermissionsbyuserId(User user, Model model) {
		Map<String, List<String>> userpermissionsmap = new LinkedHashMap<>();
		List<UPM> upmlist = uPMService.findAll(user.getUserId());
		for (UPM upm : upmlist) {
			String moduleName=upm.getModule_name();
			if (!userpermissionsmap.containsKey(moduleName)) {
				List<String> list = new ArrayList<>();
				if("Checked".equalsIgnoreCase(upm.getUser_access())) {
					list.add(upm.getPermission_name());
				 }
				if(!list.isEmpty()) {
					userpermissionsmap.put(moduleName, list);
				}
			} else {
				List<String> list = userpermissionsmap.get(moduleName);
				if("Checked".equalsIgnoreCase(upm.getUser_access())) {
					list.add(upm.getPermission_name());
				 }
				if(!list.isEmpty()) {
					userpermissionsmap.put(moduleName, list);
				}
			}
		}
		return userpermissionsmap;
	}

	
	/*
	 * @RequestMapping("/tokenExpired") public String tokenExpired() {
	 * logger.info("tokenExpired"); return "redirect:login";
	 * 
	 * }
	 */

}
