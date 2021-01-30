package com.priya.rightcare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priya.rightcare.Entity.Category;
import com.priya.rightcare.Repository.CategoryRepository;


@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;

    public Category saveOrUpdateACategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategoryById(int categoryId) {
        return categoryRepository.getByCategoryId(categoryId);
    }
    

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }


	public List<Category> saveCategories(List<Category> categories) {

		return categoryRepository.saveAll(categories);
	}

	
	public List<Category> getCategories() {

		return categoryRepository.findAll();
		
	}

	public Category getById(int categoryId) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(categoryId);
	}


	public Category getByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	
	
	
}
