package com.smerp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.admin.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
