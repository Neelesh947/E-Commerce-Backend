package com.example.demo.Service;

import javax.xml.bind.ValidationException;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.AddProductInCartDto;
import com.example.demo.Dto.OrderDto;

public interface CartService {

	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	public OrderDto getCartItemsByUserId(String userId);
	
	public OrderDto applyCoupon(String userId, String code) throws ValidationException;
	
	public OrderDto increaseOrderQuantity(AddProductInCartDto addProductInCartDto);
}
