package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findDepartmentsById(Integer id);

	List<Department> findDepartmentsByCompanyId(Integer id);

}
