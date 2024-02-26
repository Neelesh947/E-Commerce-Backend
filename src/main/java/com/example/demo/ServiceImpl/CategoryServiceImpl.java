package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(Category category) {
		String uuid=UUID.randomUUID().toString();
		category.setCategroyId(uuid);
		return this.categoryRepository.save(category);
	}

	public List<Category> getAllCategory() {
		return this.categoryRepository.findAll();
	}

	public Category getCategoryById(String id) {
		return this.categoryRepository.findById(id).orElse(null);
	}
}
