package com.smerp.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.admin.VendorAddress;
import com.smerp.repository.inventory.VendorAddressRepository;

@Service
public class VendorAddressServiceImpl implements VendorAddressService{
	
	@Autowired
	VendorAddressRepository vendorAddressRepository;

	@Override
	public VendorAddress findById(int id) {
		return vendorAddressRepository.findById(id).get();
	}

}
