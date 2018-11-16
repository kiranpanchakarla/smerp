package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.VendorAddress;

@Repository
public interface VendorAddressRepository extends JpaRepository<VendorAddress, Integer> {


}
