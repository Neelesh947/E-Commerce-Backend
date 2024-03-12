package com.example.demo.Dto;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.example.demo.Entity.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {

	private String orderId;
	private String orderDescription;
	private Date date;
	private Long amount;
	
	private String address;
	private String payment;
	private OrderStatus orderStatus;
	
	private Long totalAmount;
	
	private Long discount;
	private UUID trackingId;
	
	private String userName;
	
	private List<CartItemsDto> cartItemsDto;
	
	private String couponName;

}
