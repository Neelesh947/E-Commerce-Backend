package com.example.demo.Dto;


import org.springframework.web.multipart.MultipartFile;


import lombok.Data;

@Data
public class ReviewDto {

   private Long reviewId;
	
	private Long rating;
	
	private String description;
	
	private MultipartFile img;
	
	private byte[] returnedImg;
	
	private String userId;
	
	private String productId;
	
	private String username;
}
