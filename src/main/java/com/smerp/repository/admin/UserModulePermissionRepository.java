package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.User;
import com.smerp.model.admin.UserModulePermission;

@Repository
public interface UserModulePermissionRepository extends JpaRepository<UserModulePermission, Integer> {

	@Query("SELECT u FROM UserModulePermission u where u.user.userId=:userId")
	List<UserModulePermission> findByAllUserId(@Param("userId") Integer userId );

}
