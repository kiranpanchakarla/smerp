
package com.smerp.serviceImpl.inventory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public Product save(Product product) {
		try {
			if (product != null && product.getServiceOrProduct().equals("service")) {
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

	@Override
	public List<Product> findByIsActive() {
		// TODO Auto-generated method stubfindByIsActive(true);
		return productRepository.findByIsActive(true);
	}

	@Override
	public List<String> findAllProductNames() {
		// TODO Auto-generated method stub
		return productRepository.findByDescription();
	}

	/*@Override
    public Product save(Product product) {
        
        
        
        return productRepository.save(product);
    }*/

	@Override
	public Product findByDescription(String name) {
		return productRepository.findByDescription(name);
	}

	@Override
	public List<String> findAllProductNamesByProduct(String product) {
		
		return productRepository.findAllProductNamesByProduct(product);
	}

	@Override
	public Product findByproductNo(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByProductNo(name);
	}
	
	@Override
	public Product findLastCodeNumber(String productGroup) {
		/*return productRepository.findProductGroupByProductCode(productGroup);*/
		
		/*return (Product)entityManager.createNamedQuery("getProductCode")
				.setParameter("productName", productGroup).setMaxResults(1)
				.getSingleResult();*/
		Product  pp =null;
		
		try{
			
			Query query = entityManager.createNamedQuery("getProductCode")
		
				.setParameter("productName", productGroup).setMaxResults(1);
		logger.info("query--->" +query);
		  pp = (Product) query.getSingleResult();		
			logger.info(pp);	
		}catch (NoResultException nre){
			logger.info("nre--->" +nre);
				}	
		
		return pp;
		
	}
	
}
