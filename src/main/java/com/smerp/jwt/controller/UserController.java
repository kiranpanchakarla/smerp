package com.smerp.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smerp.jwt.models.UserDto;
import com.smerp.jwt.models.Users;
import com.smerp.service.UserService;



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<Users> listUser(){
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


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public Users saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }



}
