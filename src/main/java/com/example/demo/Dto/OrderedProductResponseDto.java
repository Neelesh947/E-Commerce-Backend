package com.example.demo.Dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderedProductResponseDto {

	private List<ProductDto> productDtoList;
	
	private Long orderAmount;
}
