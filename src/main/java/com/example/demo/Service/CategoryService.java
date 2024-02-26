package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Category;

public interface CategoryService {

	Category createCategory(Category category);

	List<Category> getAllCategory();

	Category getCategoryById(String id);

}
