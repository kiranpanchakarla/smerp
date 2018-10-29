package com.smerp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.smerp.model.admin.Desigination;
import com.smerp.service.admin.DesignationService;

@Controller("/designation")
public class DesignationController {
	
	@Autowired 
	DesignationService designationService;
	
	public void list() {
		
	List<Desigination> list=designationService.findAll();
		
		
	}

}
