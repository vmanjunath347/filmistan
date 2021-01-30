package com.priya.rightcare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.priya.rightcare.DTO.OutputResponse;
import com.priya.rightcare.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT new com.priya.rightcare.DTO.OutputResponse (c.name , p.productName) FROM Category c JOIN c.products p")
	public List<OutputResponse> getInfo();

	public Category getByCategoryId(int categoryId);




	//public Category getByName(String name);

	public Category findById(int categoryId);

	public Category findByName(String name);

	//public Category getCategoryById(int categoryId);



	

}
