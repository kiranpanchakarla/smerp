package com.smerp.jwt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.jwt.models.Constants;
import com.smerp.model.admin.Company;
import com.smerp.model.admin.Department;
import com.smerp.model.admin.Desigination;
import com.smerp.model.admin.Module;
import com.smerp.model.admin.Permission;
import com.smerp.model.admin.Role;
import com.smerp.model.admin.UPM;
import com.smerp.model.admin.User;
import com.smerp.model.admin.UserModulePermission;
import com.smerp.model.inventory.Product;
import com.smerp.repository.admin.UserModulePermissionService;
import com.smerp.service.UserService;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.admin.DesignationService;
import com.smerp.service.admin.ModuleService;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.PermissionService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.UPMService;
import com.smerp.service.master.CurrencyServices;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.RoleService;
import com.smerp.util.ContextUtil;
import com.smerp.util.FilePathUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	private static String logoUploadedPath;

	@Value(value = "${file.upload.path}")
	public void setProp(String prop) {
		this.logoUploadedPath = prop;
	}

	@Autowired
	private UserService userService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	CurrencyServices currencyServices;

	@Autowired
	DesignationService designationService;

	@Autowired
	CompanyServices companyServices;

	@Autowired
	RoleService roleService;
	
	@Autowired
	PlantService plantService;
	
	@Autowired
	ProductService  productService; 
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	PermissionService permissionService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	UserModulePermissionService userModulePermissionService;

	@Autowired
	UPMService uPMService;

	@GetMapping("/create")
	private String createPage(Model model , User user) {
		logger.info("Inside UserController createPage Method");
		try {
			Company company = getComapnyIdFromSession();
			model.addAttribute("user", new User());
			model.addAttribute("rolesList", rolesMap());
			model.addAttribute("plantList", plantService.findAll());
			usercreationdependencymodules(model, company);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// model.addAttribute("company", companyServices.findAll() );
		return "user/create";
	}

	private void usercreationdependencymodules(Model model, Company company) {
		model.addAttribute("department", departmentMap());
		model.addAttribute("desigination", desiganationMap());
		model.addAttribute("usersList", userService.findByUsersByCompany(company));
	}

	@GetMapping("/list")
	private String list(Model model) {
		logger.info("inside list method");
		List<User> list = userService.findByIsActive();
		if (list.isEmpty()) {
			return "redirect:/user/create";
		} else {
			model.addAttribute("list", list);
			return "user/list";
		}
	}

	@GetMapping(value = "/view")
	public String show(String id, Model model) {
		try {
			User user = userService.findById(Integer.parseInt(id));
			//Integer managerId = user.getReportingManagerId();
			//User userDt = userService.findById(managerId);
			model.addAttribute("plantList", plantService.findAll());
			// model.addAttribute("managername",userDt.getFirstname()+"
			// "+user.getLastname());
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "user/view";
	}

	@PostMapping(value = "/delete")
	public String delete(String id) {
		logger.info("Inside delete method");
		userService.delete(Integer.parseInt(id));
		return "redirect:list";
	}
	
	
	@GetMapping(value = "/isValidUserName")
	@ResponseBody
	public boolean isValidUserName(String name) {
		User user  = userService.findOne(name);
		if(user!=null) {
			logger.info("User Name  Already Exits!");
			return true;
		}else {
			return false;
		}
	}
	

	@GetMapping(value = "/edit")
	public String view(String id, Model model,String pwd, HttpServletRequest request) {
		logger.info("Inside delete method"+pwd);
		Company company = getComapnyIdFromSession();
		usercreationdependencymodules(model, company);
		User user = userService.findById(Integer.parseInt(id));
		Map<Long, String> map = userService.rolesMap(user.getRoles());
		if (user.getImage()!= null && !user.getImage().equals("")) {
			model.addAttribute("filePath", ContextUtil.populateContext(request) + "/" + user.getImage());
		} else {
			model.addAttribute("filePath", null);
		}
		model.addAttribute("rolesList", map);
		model.addAttribute("plantList", plantService.findAll());
		
		if(pwd!=null) 
		model.addAttribute("pwd", pwd);
		
		model.addAttribute("user", user);
		return "user/create";
	}
	
	
	@GetMapping(value = "/checkCurrentPwd")
	@ResponseBody
	public Boolean checkCurrentPwd(String currentPwd,String enterPwd) {
		logger.info("currentPwd" + currentPwd);
		logger.info("enterPwd" + enterPwd);
		return userService.checkCurrentPwd(currentPwd, enterPwd);
	}

	private Company getComapnyIdFromSession() {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getCompany();
	}

	@PostMapping(value = "/save")
	public String saveUser(@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			User user) throws IOException {
		logger.info("Inside user controller save method" + user);
		if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
			Map<String, String> path = FilePathUtil.getFilePath(file, logoUploadedPath, Constants.USERFOLDER);
			String pathToSave = path.get("pathToSave");
			String fullPath = path.get("fullPath");
			logger.info("fullPath--> " + fullPath);
			logger.info("pathToSave-->" + pathToSave);

			FilePathUtil.saveFile(file, fullPath);
			user.setImage(pathToSave);
		}
		userService.save(user);
		return "redirect:list";
	}

	@RequestMapping(value = "/getdeginations", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, Object> getDesignationsList(@RequestParam("id") String id) {
		logger.info("Inside getDesignationsList method " + id);
		List<Desigination> list = designationService.findByDepartmentId(Integer.parseInt(id));
		Map<Integer, Object> map = list.stream()
				.collect(Collectors.toMap(Desigination::getId, Desigination::getDesigination));

		logger.info("length  " + list.size());
		return map;
	}
	
	@GetMapping("/addPermissions")
	public String addPermissions(String id, Model model) {
		logger.info("user addPermissions method");
		User user = userService.findById(Integer.parseInt(id));
		Map<Module, List<Permission>> usermodulepermissionsmap = usermodulepermissionsbyuserId(user, model);
		if (usermodulepermissionsmap == null || usermodulepermissionsmap.isEmpty()) {
			usermodulepermissionsmap = new LinkedHashMap<>();
			// List<UserModulePermission> upmlist = new ArrayList<>();
			List<Module> list = moduleService.findAll();
			for (Module module : list) {
				/*
				 * UserModulePermission upm = new UserModulePermission();
				 * upm.setPermissions(permissionService.findAll()); upm.setModule(module);
				 * upm.setUser(userService.findById(Integer.parseInt(id))); upmlist.add(upm);
				 */
				usermodulepermissionsmap.put(module, permissionService.findAll());
			}
			// model.addAttribute("list", upmlist);
		}
		
		model.addAttribute("ump", usermodulepermissionsmap);
		model.addAttribute("user", user);
		model.addAttribute("id", id);
		return "user/addPermissions";
	}
	
	
	
	@PostMapping("/savePermissions")
	public String savePermission(User user, Model model) {
		logger.info("user details in savePermissions method" + user.getUserId());
		/* getting user permissions information from database */
		List<UserModulePermission> listupm = userModulePermissionService.findByAllUserId(user.getUserId());
		/* removing user permissions information from database */
		
		if(listupm.isEmpty() ||listupm!=null) {
			boolean flag = userModulePermissionService.deleteAll(listupm);
			logger.info("flag" + flag);
		}
		
		/* updating user permisions details in database */
		userModulePermissionService.saveAll(user);
		Map<Module, List<Permission>> userpermissionsmap = usermodulepermissionsbyuserId(user, model);
		/*model.addAttribute("user", user);
		model.addAttribute("id", user.getUserId());
		return "user/addPermissions";*/
		return "redirect:list";
	}
	
	private Map<Module, List<Permission>> usermodulepermissionsbyuserId(User user, Model model) {
		Map<Module, List<Permission>> userpermissionsmap = new LinkedHashMap<>();
		List<UPM> upmlist = uPMService.findAll(user.getUserId());
		for (UPM upm : upmlist) {
			Module module = new Module();
			module.setId(upm.getModule_id());
			module.setModuleName(upm.getModule_name());
			if (!userpermissionsmap.containsKey(module)) {
				Permission permissions = new Permission();
				permissions.setId(upm.getPermssion_id());
				permissions.setPermissionName(upm.getPermission_name());
				permissions.setFlag(upm.getUser_access());
				// p.setDbId(upm.getId());
				List<Permission> list = new ArrayList<>();
				list.add(permissions);
				userpermissionsmap.put(module, list);
			} else {
				List<Permission> list = userpermissionsmap.get(module);
				Permission p = new Permission();
				p.setId(upm.getPermssion_id());
				p.setPermissionName(upm.getPermission_name());
				p.setFlag(upm.getUser_access());
				// p.setDbId(upm.getId());
				list.add(p);
				userpermissionsmap.put(module, list);
			}
		}

		model.addAttribute("ump", userpermissionsmap);
		return userpermissionsmap;
	}

	
	

	public Map<Integer, Object> desiganationMap() {
		Company company = getComapnyIdFromSession();
		Map<Integer, Object> map = designationService.findDesignationsByCompanyId(company.getId()).stream()
				.collect(Collectors.toMap(Desigination::getId, Desigination::getDesigination));
		return map;
	}

	//

	public Map<Integer, Object> departmentMap() {
		Company company = getComapnyIdFromSession();
		Map<Integer, Object> map = departmentService.findDepartmentsByCompanyId(company.getId()).stream()
				.collect(Collectors.toMap(Department::getId, Department::getName));
		return map;
	}

	//

	public Map<Long, Object> rolesMap() {
		Map<Long, Object> map = roleService.findAll().stream().collect(Collectors.toMap(Role::getId, Role::getName));
		return map;
	}

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	private String getUserDetailsByUserName(@RequestParam("username") String username) throws JsonProcessingException {
		
		User user =userService.findByName(username);
        if(user!=null) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper.writeValueAsString(user);
        }else {
        	return "";
        }
		
	}

}
