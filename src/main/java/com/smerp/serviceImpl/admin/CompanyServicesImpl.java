package com.smerp.serviceImpl.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smerp.dao.UserDao;
import com.smerp.model.admin.Company;
import com.smerp.model.admin.Role;
import com.smerp.model.admin.User;
import com.smerp.repository.admin.CompanyRepository;
import com.smerp.service.admin.CompanyServices;
import com.smerp.service.master.RoleService;

@Service
public class CompanyServicesImpl implements CompanyServices {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	private static final Logger logger = LogManager.getLogger(CompanyServicesImpl.class);

	public Company save(Company company) {
		try {
			int first_flag=0;
			if(company.getId()==null) {
				first_flag=1;
			}
			
			 company = companyRepository.save(company);
			logger.info("Company Obj-->" +company);
				
				if(first_flag==1) {
					User user = new User();	
					logger.info("user Obj-->" +user);
					user.setCompany(company);
					user.setUsername(company.getEmailId());
					user.setActivationId("InActive");
					//user.setPlant("test");
					user.setPassword(bcryptEncoder.encode("Welcome"));
					user.setImage(company.getLogo());
					user.setFirstname(company.getName());
				    user.setLastname(company.getName());
					user.setMobileNo(company.getPhoneNum());
					user.setUserEmail(company.getEmailId());
					user.setEnabled(true);
					user.setReportingManagerId(company.getId());
					//user.setUsername(RandomUtil.referenceId());
					String roleId = "1";
					Role role = roleService.findById(Long.parseLong(roleId));
					Set<Role> roles = new HashSet<>();
					roles.add(role);
					user.setRoles(roles);
					userDao.save(user);
				}
		
			
		} catch (Exception ex) {
			logger.info("error-->" + ex.getMessage());
		}
		return company;
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	
	public List<Company> findByIsActive() {
		return companyRepository.findByIsActive(true);
	}
	
	
	
	public Company findByName(String name) {
		return companyRepository.findByName(name);
	}
	
	public Company findByEmailId(String emailId) {
		return companyRepository.findByEmailId(emailId);
	}
	

	public Company getInfo(int companyId) {
		return companyRepository.findById(companyId);
	}

	public void delete(int companyId) {
		
		Company company = companyRepository.findById(companyId);
		company.setIsActive(false);
		companyRepository.save(company);
		List<User> users = userDao.findByCompanyId(companyId);
		for(User user:users) {
			user.setEnabled(false);
			userDao.save(user);
		}
		//companyRepository.deleteById(companyId);
	}

}
