package com.example.demo.Entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.Dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {

	@Id
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
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="coupon_id",referencedColumnName = "id")
	private Coupon coupon;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<CartItem> cartItems;
	
	public OrderDto getOrderDto() {
		OrderDto orderDto=new OrderDto();
		
		orderDto.setOrderId(orderId);
		orderDto.setOrderDescription(orderDescription);
		orderDto.setAddress(address);
		orderDto.setTrackingId(trackingId);
		orderDto.setAmount(amount);
		orderDto.setDate(date);
		orderDto.setOrderStatus(orderStatus);
		orderDto.setUserName(user.getUsername());
		
		if(coupon !=null)
		{
			orderDto.setCouponName(coupon.getName());
		}
		return orderDto;
	}
}
