package com.smerp.jwt.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.smerp.jwt.models.UserDto;
import com.smerp.model.admin.User;
import com.smerp.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;
    
    
    @GetMapping("/create")
    private String createPage() {
    	logger.trace("Inside UserController createPage Method");
    	
    	return "user/create";
    }
    

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
   //@PreAuthorize("hasRole('ADMIN')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getOne(@PathVariable(value = "id") Long id,Model model){
    	System.out.println("in user controller");
    	model.addAttribute("data", userService.findById(id));
        return "home";
    }


    @RequestMapping(value="/create", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    
  


}
