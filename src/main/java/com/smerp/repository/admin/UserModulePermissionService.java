package com.smerp.repository.admin;

import java.util.List;
import java.util.Map;

import com.smerp.model.admin.Module;
import com.smerp.model.admin.Permission;
import com.smerp.model.admin.User;
import com.smerp.model.admin.UserModulePermission;

public interface UserModulePermissionService {

	List<UserModulePermission>  saveAll(User user);

	boolean deleteAll(List<UserModulePermission> listupm);

	List<UserModulePermission> findByAllUserId(Integer userId);


}
