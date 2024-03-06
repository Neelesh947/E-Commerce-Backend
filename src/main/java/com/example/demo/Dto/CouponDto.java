package com.example.demo.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class CouponDto {

	private String couponId;
	
	private String name;
	
	private String code;
	
	private Long discount;
	
	private Date expirationDate;
}
