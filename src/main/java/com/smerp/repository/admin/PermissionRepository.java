package com.smerp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
