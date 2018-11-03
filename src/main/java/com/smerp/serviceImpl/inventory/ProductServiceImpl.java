package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.Product;
import com.smerp.repository.inventory.ProductRepository;
import com.smerp.service.inventory.ProductService;

@Service
public class ProductServiceImpl  implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;


	@Override
	public Product save(Product product) {
		try {
			productRepository.save(product);
		}catch(Exception ex) {
			System.out.println("error-->" +ex.getMessage());
		}
		return product;

}


	@Override
	public List<Product> productList() {
		
		return productRepository.findAll();
	}
	
	
	@Override
	public List<Product> findByIsActive(Boolean isActive) {
		
		return productRepository.findByIsActive(isActive);
	}



	@Override
	public Product getInfo(int id) {
		
		Product obj = productRepository.findById(id).get();
		
		return obj;
	}


	


	@Override
	public Product update(Product product) {
		try {
			productRepository.save(product);
		}catch(Exception ex) {
			System.out.println("error-->" +ex.getMessage());
		}
		return product;
	}


	@Override
	public Product delete(int id) {
		
		Product product=productRepository.findById(id).get();
		product.setIsActive(false);;
		productRepository.save(product);
		
		return null;
	}
}
