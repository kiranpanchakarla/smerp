package com.smerp.serviceImpl.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Company;
import com.smerp.repository.admin.CompanyRepository;
import com.smerp.service.admin.CompanyServices;

@Service
public class CompanyServicesImpl implements CompanyServices {

	@Autowired
	CompanyRepository companyRepository;

	private static final Logger logger = LogManager.getLogger(CompanyServicesImpl.class);

	public Company save(Company company) {
		try {
			companyRepository.save(company);
		} catch (Exception ex) {
			logger.info("error-->" + ex.getMessage());
		}
		return company;
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Company getInfo(int companyId) {
		return companyRepository.findById(companyId);
	}

	public void delete(int companyId) {
		companyRepository.deleteById(companyId);
	}

}
