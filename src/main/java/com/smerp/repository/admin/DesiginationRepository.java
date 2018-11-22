package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Desigination;



@Repository
public interface DesiginationRepository extends JpaRepository<Desigination, Integer> {

	List<Desigination> findDesignationsByCompanyId(Integer id);

	List<Desigination> findByDepartmentId(int id);

	Desigination findById(int id);
}
