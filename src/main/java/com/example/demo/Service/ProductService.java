package com.example.demo.Service;

import java.io.IOException;
import java.util.List;

import com.example.demo.Dto.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto productDto) throws IOException;
	
	public List<ProductDto> getAllProducts();
	
	
	//get by name
	public List<ProductDto> getProductsByName(String name);
	
	public boolean deleteProducts(String id);
	
	public ProductDto getProductById(String productId);
	
	public ProductDto updateTheProduct(String productId, ProductDto productDto) throws IOException;
}
