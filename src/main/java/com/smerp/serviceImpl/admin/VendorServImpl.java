package com.smerp.serviceImpl.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.admin.Vendor;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.repository.admin.VendorRepository;
import com.smerp.service.admin.VendorService;


@Service
public class VendorServImpl  implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	@PersistenceContext    
	private EntityManager entityManager;
	
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
	
	
	@Override
	public Vendor findLastCodeNumber() {
		return vendorRepository.findTopByOrderByIdDesc();
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


	@Override
	public List<String> findAllVendorNames() {
		// TODO Auto-generated method stub
		return vendorRepository.findAllVendorNames();
	}


	@Override
	public Vendor findByName(String name) {
		return vendorRepository.findByName(name);
	}


	@Override
	public Vendor findById(int id) {
		return vendorRepository.findById(id);
	}

	@Override
	public String getTotalAmt(int id) {
		
		String sql= " select ih.vendor_id ,sum(vil.inv_amount_tax) inv_amount_tax FROM tbl_invoice ih " + 
				" JOIN vw_invoice_lineitems_amount vil ON ih.id = vil.id " + 
				" where ih.status!= 'Rejected' " + 
				" group by ih.vendor_id having ih.vendor_id= " +id;
		
		logger.info("sql ----> " + sql);
		Query query = entityManager.createNativeQuery(sql);
		  List<Object[]>	list = query.getResultList();
		
		logger.info("List Size -----> " + list.size());
		String amount = "";
	     for(Object[] tuple : list) {
	    	 amount = tuple[1] == null ? "0" : (tuple[1]).toString();
	     }
		
		
		return amount;
	}
	

	@Override
	public Vendor findByCode(String vendorCode) {
		// TODO Auto-generated method stub
		return vendorRepository.findByCode(vendorCode);
	}
	
}
