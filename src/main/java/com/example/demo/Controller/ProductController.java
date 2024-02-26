package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//create products
	@PostMapping("/")
	public ResponseEntity<ProductDto> createProduct(@ModelAttribute ProductDto productDto) throws IOException
	{
		ProductDto productDto1=this.productService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
	}
	
	//get all products
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		List<ProductDto> productDtos=this.productService.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(productDtos);
	}
}
