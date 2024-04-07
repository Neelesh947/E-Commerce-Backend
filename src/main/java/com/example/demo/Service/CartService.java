package com.example.demo.Service;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import org.springframework.http.ResponseEntity;

import com.example.demo.Dto.AddProductInCartDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.Dto.PlaceOrderDto;

public interface CartService {

	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	public OrderDto getCartItemsByUserId(String userId);
	
	public OrderDto applyCoupon(String userId, String code) throws ValidationException;
	
	public OrderDto increaseOrderQuantity(AddProductInCartDto addProductInCartDto);
	
	public OrderDto decreaseOrderQuantity(AddProductInCartDto addProductInCartDto);
	
	public OrderDto placeOrderDto(PlaceOrderDto placeOrderDto);
	
	public List<OrderDto> getMyPlacedOrder(String userId);
	
	public OrderDto searchOrderByTrackingid(UUID trackingId);
}
