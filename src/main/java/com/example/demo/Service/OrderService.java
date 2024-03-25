package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.OrderDto;

public interface OrderService {

	public List<OrderDto> getAllPlacedOrder();
	
	public OrderDto changeOrderStatus(String orderId, String status);
}
