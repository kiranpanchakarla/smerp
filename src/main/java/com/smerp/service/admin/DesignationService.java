package com.smerp.service.admin;

import java.util.List;
import com.smerp.model.admin.Desigination;

public interface DesignationService {

	List<Desigination> findAll();

	List<Desigination> findDesignationsByCompanyId(Integer id);

	List<Desigination> findByDepartmentId(int id);

	Desigination save(Desigination designation);

	Desigination findById(int id);

	Desigination getInfo(int id);

	void delete(int id);
	
	boolean isValid(String name,Integer deptId);
	
	List<Desigination> findByName(String name);

}
