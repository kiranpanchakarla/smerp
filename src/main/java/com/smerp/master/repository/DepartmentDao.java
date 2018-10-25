package com.smerp.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smerp.model.admin.Department;

public interface DepartmentDao extends JpaRepository<Department, Integer> {

}
