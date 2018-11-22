package com.smerp.service.admin;

import java.util.List;
import com.smerp.model.admin.Department;

public interface DepartmentService {

	List<Department> findAll();

	List<Department> findDepartmentsByCompanyId(Integer id);

	Department save(Department department);

	Department findById(int id);

	Department getInfo(int id);

	void delete(int id);

}
