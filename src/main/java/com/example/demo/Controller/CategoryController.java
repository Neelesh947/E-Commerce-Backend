package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Category;
import com.example.demo.Service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create Category
	@PostMapping("/")
	public ResponseEntity<Category> createcategory(@RequestBody Category category)
	{
		Category cat=this.categoryService.createCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}
	
	//get all category
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategory()
	{
		List<Category> cat=this.categoryService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}
	
	//get Category by Id
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable String id)
	{
		Category cat=this.categoryService.getCategoryById(id);
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}
}
