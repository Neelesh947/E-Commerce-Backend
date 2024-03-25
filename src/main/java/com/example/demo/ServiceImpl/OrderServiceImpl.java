package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.OrderDto;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderStatus;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	//get all order list
	public List<OrderDto> getAllPlacedOrder(){
		List<Order> orderList=this.orderRepository.
					findAllByOrderStatusIn(List.of(OrderStatus.PLACED, OrderStatus.SHIPPED, OrderStatus.DELIVERED));
		
		return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
	}
	
	//change the status of the orders
	public OrderDto changeOrderStatus(String orderId, String status)
	{
		Optional<Order> optionalOrder=this.orderRepository.findById(orderId);
		if(optionalOrder.isPresent())
		{
			Order order=optionalOrder.get();
			
			if(Objects.equals(status, "Shipped"))
			{
				order.setOrderStatus(OrderStatus.SHIPPED);
			}
			else if(Objects.equals(status, "Delivered"))
			{
				order.setOrderStatus(OrderStatus.DELIVERED);
			}
			
			return orderRepository.save(order).getOrderDto();
		}
		return null;
	}
	
}
