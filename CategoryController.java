package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advice.CategoryException;
import com.entity.Category;
import com.serviceImpl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    public List<Category> getAllCategories() throws Throwable {
        return categoryServiceImpl.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId) throws Throwable {
        return categoryServiceImpl.getCategoryById(categoryId);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category)  {
        return categoryServiceImpl.createCategory(category);
    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category updatedCategory) throws Throwable {
        return categoryServiceImpl.updateCategory(categoryId, updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) throws Throwable {
        categoryServiceImpl.deleteCategory(categoryId);
    }
}
