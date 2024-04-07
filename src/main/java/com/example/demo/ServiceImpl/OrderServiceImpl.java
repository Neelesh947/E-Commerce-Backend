package com.example.demo.ServiceImpl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AnalyticsResponseDto;
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
	
	//Analytics
	public AnalyticsResponseDto calculateAnalytics()
	{
		LocalDate currentDate=LocalDate.now();		
		LocalDate previousMonthDate=currentDate.minusMonths(1);
		
		Long currentMonthsOrders=getTotalOrdersOfTheMonths(currentDate.getMonthValue(),currentDate.getYear());
		Long previousMonthsOrders=getTotalOrdersOfTheMonths(previousMonthDate.getMonthValue(),previousMonthDate.getYear());
		
		Long currentMonthsEarnings=getTotalearningsOfTheMonths(currentDate.getMonthValue(),currentDate.getYear());
		Long previousMonthsEarnings=getTotalearningsOfTheMonths(previousMonthDate.getMonthValue(),previousMonthDate.getYear());
		
		Long placed=orderRepository.countByOrderStatus(OrderStatus.PLACED);
		Long shipped=orderRepository.countByOrderStatus(OrderStatus.SHIPPED);
		Long delivered=orderRepository.countByOrderStatus(OrderStatus.DELIVERED);
		
		return new AnalyticsResponseDto(placed, shipped, delivered, currentMonthsOrders, previousMonthsOrders, 
						currentMonthsEarnings, previousMonthsEarnings);
		
	}
	
	public Long getTotalOrdersOfTheMonths(int month, int year) {
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Date startOfMonth=calendar.getTime();
		//move the calendar to the end of the specific months
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		Date endOfMonths=calendar.getTime();
		List<Order> orders=this.orderRepository.findByDateBetweenAndOrderStatus(startOfMonth,endOfMonths, OrderStatus.DELIVERED);
		
		return (long)orders.size();
	}
	
	public Long getTotalearningsOfTheMonths(int month, int year) {
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Date startOfMonth=calendar.getTime();
		//move the calendar to the end of the specific months
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		Date endOfMonths=calendar.getTime();
		List<Order> orders=this.orderRepository.findByDateBetweenAndOrderStatus(startOfMonth,endOfMonths, OrderStatus.DELIVERED);
		
		Long sum=0L;
		for(Order order:orders)
		{
			sum+=order.getAmount();
		}
		return sum;
	}
	
}
