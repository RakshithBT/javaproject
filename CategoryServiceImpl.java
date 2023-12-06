package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.CategoryException;
import com.entity.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
    
    public List<Category> getAllCategories() throws CategoryException {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new CategoryException("No categories found");
        }
        return categories;
    }


    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    



    public Category updateCategory(Long categoryId, Category updatedCategory)throws CategoryException {
        if (!categoryRepository.existsById(categoryId)) {
            // Handle not found
            throw new CategoryException("Category not found with id: " + categoryId);
        }
        updatedCategory.setId(categoryId);
        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Long categoryId) throws CategoryException{
        if (!categoryRepository.existsById(categoryId)) {
            // Handle not found
            throw new CategoryException("Category not found with id: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }
}

