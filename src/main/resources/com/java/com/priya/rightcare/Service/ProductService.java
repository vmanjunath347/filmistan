package com.priya.rightcare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.priya.rightcare.Entity.Product;
import com.priya.rightcare.Repository.ProductRepository;


@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;

    public Product saveOrUpdateACategory(Product product) {
        return productRepository.save(product);
    }

    public Product findByProductId(int productId) {
        return productRepository.getByProductId(productId);
    }

    public Product findByProductName(String productName) {
        return productRepository.getByProductName(productName);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
    public List<Product> getProducts() {

		return productRepository.findAll();
	}

	public List<Product> saveProducts(List<Product> products) {
		// TODO Auto-generated method stub
		return productRepository.saveAll(products);
	}


}
