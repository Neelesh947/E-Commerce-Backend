package com.example.demo.ServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ProductDto addProduct(ProductDto productDto) throws IOException
	{
		Product product=new Product();
		
		String uuid=UUID.randomUUID().toString();
		product.setProductId(uuid);		
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImg(productDto.getImg().getBytes());
		
		Category category=categoryRepository.findById(productDto.getCategroyId()).orElseThrow();
		
		product.setCategory(category);
		return productRepository.save(product).getDto();
	}
	
	//get all products
	public List<ProductDto> getAllProducts(){
		List<Product> products=this.productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	//get the products by name
	public List<ProductDto> getProductsByName(String name){
		List<Product> products=this.productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	//delete the products
	public boolean deleteProducts(String id)
	{
		Optional<Product> products=this.productRepository.findById(id);
		if(products.isPresent())
		{
			this.productRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
