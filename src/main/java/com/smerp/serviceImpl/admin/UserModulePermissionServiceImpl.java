package com.smerp.serviceImpl.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Permission;
import com.smerp.model.admin.User;
import com.smerp.model.admin.UserModulePermission;
import com.smerp.repository.admin.UserModulePermissionRepository;
import com.smerp.repository.admin.UserModulePermissionService;
import com.smerp.service.UserService;
import com.smerp.service.inventory.UPMService;

@Service
public class UserModulePermissionServiceImpl implements UserModulePermissionService {
	
	private static final Logger logger = LogManager.getLogger(UserModulePermissionServiceImpl.class);


	@Autowired
	UserModulePermissionRepository userModulePermissionRepository;

	@Autowired
	UserService userService;

	@Autowired
	UPMService uPMService;


	@Override
	public List<UserModulePermission> saveAll(User user) {
		
		List<UserModulePermission> listDt=user.getUserModulePermission();
		logger.info("UserModulePermission size"+listDt.size());
		// saving user mosule info in database
		List<UserModulePermission> usermodulepermissionslist = new ArrayList<UserModulePermission>();
		for (UserModulePermission userModulePermission : listDt) {
			System.out.println(userModulePermission);
			List<Permission> listpermissions = userModulePermission.getPermissions();
			if(listpermissions!=null) {
				for (Permission permission : listpermissions) {
					UserModulePermission usermodulepermissiondetails = new UserModulePermission();
					usermodulepermissiondetails.setModule(userModulePermission.getModule());
					usermodulepermissiondetails.setPermission(permission);
					usermodulepermissiondetails.setUser(userService.findById(userModulePermission.getUser().getUserId()));
					usermodulepermissionslist.add(usermodulepermissiondetails);
				}
			}
			
		}

		userModulePermissionRepository.saveAll(usermodulepermissionslist);
		
		return listDt;
	}

	@Override
	public List<UserModulePermission> findByAllUserId(Integer userId) {
		return userModulePermissionRepository.findByAllUserId(userId);
	}

	@Override
	public boolean deleteAll(List<UserModulePermission> listupm) {
		try {
			userModulePermissionRepository.deleteAll(listupm);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
