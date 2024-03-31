package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.FAQdto;
import com.example.demo.Dto.ProductDetailsDto;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Service.FAQService;
import com.example.demo.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FAQService faqService;
	
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
	
	//get all products by name
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductsbyName(@PathVariable String name)
	{
		List<ProductDto> productDtos=this.productService.getProductsByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(productDtos);
	}
	
	//delete the products
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String productId)
	{
		boolean deleted=this.productService.deleteProducts(productId);
		if(deleted)
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/FAQ/{productId}")
	public ResponseEntity<FAQdto> postFAQ(@PathVariable String productId, @RequestBody FAQdto faQdto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faQdto));
	}
	
	//update the product
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable String productId)
	{
		ProductDto productDto=this.productService.getProductById(productId);
		if(productDto!=null) {
			return ResponseEntity.ok(productDto);
		}
		else {
			return ResponseEntity.notFound().build();				
		}	
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @ModelAttribute ProductDto productDto) throws IOException
	{
		ProductDto updateProduct=this.productService.updateTheProduct(productId, productDto);
		if(updateProduct!=null) {
			return ResponseEntity.ok(productDto);
		}
		else {
			return ResponseEntity.notFound().build();				
		}
	}
	
	@GetMapping("/details/{productId}")
	public ResponseEntity<ProductDetailsDto> getProductDetailsById(@PathVariable String productId)
	{
		ProductDetailsDto productDetailsDto=this.productService.getProductDetailById(productId);
		if(productDetailsDto==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDetailsDto);
	}
}
