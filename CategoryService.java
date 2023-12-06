package com.service;

import java.util.List;

import com.advice.CategoryException;
import com.entity.Category;

public interface CategoryService {

	public List<Category> getAllCategories() throws CategoryException;

	public Category getCategoryById(Long categoryId) throws CategoryException;
	
	 public Category createCategory(Category category) throws CategoryException;
	 
	 public Category updateCategory(Long categoryId, Category updatedCategory) throws CategoryException;
	 
	 public void deleteCategory(Long categoryId) throws CategoryException;

}
