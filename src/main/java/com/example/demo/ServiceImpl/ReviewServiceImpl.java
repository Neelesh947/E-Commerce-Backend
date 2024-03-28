package com.example.demo.ServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.OrderedProductResponseDto;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Dto.ReviewDto;
import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.Review;
import com.example.demo.Entity.User;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//get product details 
	public OrderedProductResponseDto getOrderProductDetailsByOrderId(String orderId)
	{
		Optional<Order> optionalOrder=orderRepository.findById(orderId);
		OrderedProductResponseDto orderedProductResponseDto=new OrderedProductResponseDto();
		
		if(optionalOrder.isPresent())
		{
			orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList=new ArrayList<>();
			
			for(CartItem cartItem:optionalOrder.get().getCartItems())
			{
				ProductDto productDto=new ProductDto();
				productDto.setProductId(cartItem.getProduct().getProductId());
				productDto.setName(cartItem.getProduct().getName());
				productDto.setPrice(cartItem.getPrice());
				productDto.setQuantity(cartItem.getQuantity());
				productDto.setByteImg(cartItem.getProduct().getImg());
				
				productDtoList.add(productDto);
			}
			orderedProductResponseDto.setProductDtoList(productDtoList);
		}
		return orderedProductResponseDto;
	}
	
	//post review
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException
	{
		Optional<Product> optionalProduct=productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser=this.userRepository.findById(reviewDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Review review=new Review();
			
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			
			review.setImg(reviewDto.getImg().getBytes());
			
			return reviewRepository.save(review).getDto();
		}
		return null;
	}
}
