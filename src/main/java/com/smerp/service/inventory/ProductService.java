package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.Product;

public interface ProductService {

	Product save(Product product);

	List<Product> productList();

	Product getInfo(int id);



	Product update(Product product);

	Product delete(int id);

	/*void delete(int parseInt);*/

}