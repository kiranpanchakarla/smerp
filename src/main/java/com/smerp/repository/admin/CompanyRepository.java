package com.smerp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
