package com.smerp.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.inventory.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}