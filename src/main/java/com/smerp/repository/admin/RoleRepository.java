package com.smerp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findById(long id);

}
