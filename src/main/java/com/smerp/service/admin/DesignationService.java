package com.smerp.service.admin;

import java.util.List;

import com.smerp.model.admin.Desigination;

public interface DesignationService {

	List<Desigination> findAll();

	List<Desigination> findDesignationsByCompanyId(Integer id);

	List<Desigination> findByDepartmentId(int id);

}
