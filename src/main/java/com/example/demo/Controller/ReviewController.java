package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.OrderedProductResponseDto;
import com.example.demo.Dto.ReviewDto;
import com.example.demo.Service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/order-product/{orderId}")
	public ResponseEntity<OrderedProductResponseDto> getOrderedProductDetailsByOrder(@PathVariable String orderId)
	{
		return ResponseEntity.ok(reviewService.getOrderProductDetailsByOrderId(orderId));
	}
	
	//Post Review
	@PostMapping("/giveReview")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException
	{
		ReviewDto reviewDto2=reviewService.giveReview(reviewDto);
		if(reviewDto2==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto2);
	}
}
