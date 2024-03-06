package com.example.demo.Service;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.AddProductInCartDto;
import com.example.demo.Dto.OrderDto;

public interface CartService {

	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	public OrderDto getCartItemsByUserId(String userId);
}
