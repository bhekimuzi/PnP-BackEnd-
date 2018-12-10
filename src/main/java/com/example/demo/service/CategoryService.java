package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void saveCategory(Category category) {
		
		categoryRepository.save(category);
		}

	public Category getCategory(long categoryId) {
		
		return categoryRepository.findOne(categoryId);
		
	}
	public List<Category> getAllCategory(){
		
		return categoryRepository.findAll();
	}
	public List<Category> getAllCategoryByAisle(long aisleId){
		return  categoryRepository.getAll(aisleId);
	}

	
}
