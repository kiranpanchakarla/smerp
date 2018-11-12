package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.Product;
import com.smerp.repository.inventory.ProductRepository;
import com.smerp.service.inventory.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public Product save(Product product) {
		try {
			if (product != null && product.getserviceOrProduct().equals("service")) {
				product.setHsnCode(null);
				
			} else {
				product.setSacCode(null);
				
			}

			logger.info("product-->" + product);
			productRepository.save(product);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error-->" + ex.getMessage());
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
	public List<Product> findAll() {
		return productRepository.findAll();
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
		} catch (Exception ex) {
			System.out.println("error-->" + ex.getMessage());
		}
		return product;
	}

	@Override
	public Product delete(int id) {

		Product product = productRepository.findById(id).get();
		product.setIsActive(false);
		productRepository.save(product);

		return product;
	}

	@Override
	public Product findByProductNo(String productNo) {

		return productRepository.findByProductNo(productNo);
	}
}
