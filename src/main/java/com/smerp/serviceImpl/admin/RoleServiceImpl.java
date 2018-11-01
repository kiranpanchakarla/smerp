package com.smerp.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Role;
import com.smerp.repository.admin.RoleRepository;
import com.smerp.service.master.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	RoleRepository  roleRepository;
	
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}


	@Override
	public Role findById(long id) {
		return roleRepository.findById(id);
	}

}
