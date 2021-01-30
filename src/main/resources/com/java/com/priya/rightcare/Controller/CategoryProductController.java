package com.priya.rightcare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priya.rightcare.DTO.InputRequest;
import com.priya.rightcare.DTO.OutputResponse;
import com.priya.rightcare.Entity.Category;

import com.priya.rightcare.Entity.Product;
import com.priya.rightcare.Repository.CategoryRepository;
import com.priya.rightcare.Service.CategoryService;
import com.priya.rightcare.Service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class CategoryProductController {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductService service;

	@Autowired
	private CategoryService categoryService;

// add more than one categories
	@PostMapping("/addCategories")
	public List<Category> addCategories(@RequestBody List<Category> categories) {

		return categoryService.saveCategories(categories);
	}

	// get all categories
	@GetMapping("/getCategories")
	public List<Category> findAllCategories() {

		return categoryService.getCategories();

	}

	@GetMapping("/Getcategory/{id}")
	public Category findById(@PathVariable  int CategoryId){
		return  categoryService.getById(CategoryId);

	}

	@GetMapping("/getcategory/{name}")
	public Category findByCategoryName(@PathVariable  String name){
		return  categoryService.getByName(name);

	}

	@PostMapping("/addmedicines")
	public List<Product> addProducts(@RequestBody List<Product> products) {

		return service.saveProducts(products);
	}

	@GetMapping("/getproducts")
	public List<Product> findAllProducts1() {
		return service.getProducts();
	}

	// add category with products(one or more products)
	@PostMapping("/input")
	public Category input(@RequestBody InputRequest request) {

		return categoryRepository.save(request.getCategory());
	}

	// get list of category with list of ordered products
	@GetMapping("/findproducts")
	public List<Category> findAllProducts() {

		return categoryRepository.findAll();
	}

	// get category name with product name
	@GetMapping("/getInformation")
	public List<OutputResponse> getInfo() {

		return categoryRepository.getInfo();
	}
	
	

}
