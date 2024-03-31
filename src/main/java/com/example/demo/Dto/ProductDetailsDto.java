package com.example.demo.Dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDetailsDto {

	private ProductDto productDto;
	
	private List<ReviewDto> reviewDtos;
	
	private List<FAQdto>  faQdtos;
}
