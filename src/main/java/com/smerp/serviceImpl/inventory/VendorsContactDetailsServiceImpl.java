package com.smerp.serviceImpl.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.repository.inventory.VendorsContactDetailsRepository;
import com.smerp.service.inventory.VendorsContactDetailsService;

@Service
public class VendorsContactDetailsServiceImpl implements VendorsContactDetailsService {

	@Autowired
	VendorsContactDetailsRepository vendorsContactDetailsRepository;
	
	
	
	@Override
	public VendorsContactDetails findById(int id) {
		return vendorsContactDetailsRepository.findById(id).get();
	}

}
