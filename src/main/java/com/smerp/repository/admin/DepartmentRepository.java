package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findDepartmentsById(Integer id);

	List<Department> findDepartmentsByCompanyId(Integer id);
	
	Department findById(int id);

	@Query("SELECT c FROM Department c WHERE LOWER(c.name) = LOWER(:name) ")
	Department findByName(@Param("name") String name);
	
	 
	
	
	
}
