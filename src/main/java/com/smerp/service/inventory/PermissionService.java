package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.admin.Permission;

public interface PermissionService {

	List<Permission> findAll();

	Permission findById(String string);

}
