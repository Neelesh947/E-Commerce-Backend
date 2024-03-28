package com.example.demo.Dto;

import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class ProductDto {

	@Id
	private String productId;
	
	private String name;
	
	private Long price;
	
	private String description;
	
	private byte[] byteImg;
	
	private String categroyId;
	
	private String categroyName;
	
	private MultipartFile img;
	
	private Long quantity;
}
