package com.smerp.controller.admin;

import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smerp.model.admin.Permission;
import com.smerp.model.admin.Role;
import com.smerp.service.inventory.PermissionService;
import com.smerp.service.master.RoleService;

@RequestMapping("/role")
@Controller
public class RoleController {
	
	private static final Logger logger = LogManager.getLogger(RoleController.class);
	
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/create")
	public String create(Role role,Model model) {
		logger.info("Inside create page method");
		model.addAttribute("permissionMap",  permissionService.findAll());
		model.addAttribute("role", role);
		return "role/create";
	}
	
	@PostMapping("/save")
	public String save(Role role) {
		logger.info("Inside save method"+role);
		
		//roleService.save(role);
		
		return "role/create";
	}

	
	private Map<Integer,Object> permissionMap(){
		return permissionService.findAll().stream().collect(Collectors.toMap(Permission::getId, Permission::getPermissionName));
	}
	

}
