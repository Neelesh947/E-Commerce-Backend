package com.example.demo.Service;

import java.io.IOException;
import java.util.List;

import com.example.demo.Dto.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto productDto) throws IOException;
	
	public List<ProductDto> getAllProducts();
}
