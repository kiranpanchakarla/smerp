package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Permission;
import com.smerp.repository.admin.PermissionRepository;
import com.smerp.service.inventory.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	
	@Autowired
	PermissionRepository  permissionRepository;
	
	
	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}


	@Override
	public Permission findById(String id) {
		return permissionRepository.findById(Integer.parseInt(id)).get();
	}

}
