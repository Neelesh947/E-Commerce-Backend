package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AnalyticsResponseDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.Service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/placedOrder")
	public ResponseEntity<List<OrderDto>> getAllPlacedOrder()
	{
		return ResponseEntity.ok(orderService.getAllPlacedOrder());
	}
	
	@GetMapping("/{orderId}/{status}")
	public ResponseEntity<?> changeOrderStatus(@PathVariable String orderId, @PathVariable String status)
	{
		OrderDto orderDto=this.orderService.changeOrderStatus(orderId, status);
		if(orderDto==null)
		{
			return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	@GetMapping("/analytics")
	public ResponseEntity<AnalyticsResponseDto> getAnalytics()
	{
		return ResponseEntity.ok(orderService.calculateAnalytics());
	}
}
