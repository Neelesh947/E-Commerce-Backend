package com.example.demo.Service;

import java.io.IOException;

import com.example.demo.Dto.OrderedProductResponseDto;
import com.example.demo.Dto.ReviewDto;

public interface ReviewService {

	public OrderedProductResponseDto getOrderProductDetailsByOrderId(String orderId);
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
