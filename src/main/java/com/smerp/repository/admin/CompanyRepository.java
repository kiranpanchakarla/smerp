package com.smerp.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("SELECT c FROM Company c WHERE LOWER(c.name) = LOWER(:name)")
	Company findByName(@Param("name") String name);

	Company findByEmailId(String emailId);

	Company findById(int id);

	@Query("SELECT c FROM Company c WHERE isActive=:isActive order by createdAt asc")
    List<Company>  findByIsActive(@Param("isActive") Boolean isActive);
}
