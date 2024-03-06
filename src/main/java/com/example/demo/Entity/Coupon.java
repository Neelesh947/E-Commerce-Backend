package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Coupon {

	@Id
	private String couponId;
	
	private String name;
	
	private String code;
	
	private Long discount;
	
	private Date expirationDate;
}
