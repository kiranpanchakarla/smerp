package com.smerp.service.master;

import java.util.List;

import com.smerp.model.admin.Role;

public interface RoleService {
	
	List<Role> findAll();

	Role findById(long parseLong);

}
