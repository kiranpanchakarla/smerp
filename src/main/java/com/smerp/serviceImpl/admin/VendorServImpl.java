package com.smerp.serviceImpl.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Company;
import com.smerp.model.admin.User;
import com.smerp.model.admin.Vendor;
import com.smerp.repository.admin.VendorRepository;
import com.smerp.service.admin.VendorService;


@Service
public class VendorServImpl  implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	private static final Logger logger = LogManager.getLogger(VendorServImpl.class);

	
	public Vendor save(Vendor vendor) {
		try {
			vendor = vendorRepository.save(vendor);
		} catch (Exception ex) {
			logger.info("error-->" + ex.getMessage());
		}
		return vendor;
	}
	
	
	public List<Vendor> findAll() {
		return vendorRepository.findAll();
	}
	
	
	public List<Vendor> findByIsActive() {
		return vendorRepository.findByIsActive(true);
	}
	
	public void delete(int vaendorId) {
		Vendor company = vendorRepository.findById(vaendorId);
		company.setIsActive(false);
		vendorRepository.save(company);
	}


	@Override
	public Vendor getInfo(int id) {
		return vendorRepository.findById(id);
	}
	
}
