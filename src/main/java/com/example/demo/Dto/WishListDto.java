package com.example.demo.Dto;

import lombok.Data;

@Data
public class WishListDto {
	
	private String userId;
	
	private String productId;
	
	private Long wishId;
	
	private String productName;
	
	private String productDescription;
	
	private byte[] returnedImg;
	
	private Long price;
}
