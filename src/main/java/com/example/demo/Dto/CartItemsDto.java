package com.example.demo.Dto;

import lombok.Data;

@Data
public class CartItemsDto {

	private String cartId;
	private Long price;
	private Long quantity;
	private String productId;
	private String orderId;
	private String userId;
	
	private String productName;
	private byte[] returnedImg;
}
