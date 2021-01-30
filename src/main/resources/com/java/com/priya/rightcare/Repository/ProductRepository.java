package com.priya.rightcare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.priya.rightcare.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product getByProductId(int productId);

	Product getByProductName(String productName);

	//Product getProductById(int productId);

	



	

}
