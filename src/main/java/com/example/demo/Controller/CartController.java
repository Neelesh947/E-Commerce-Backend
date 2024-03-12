package com.example.demo.Controller;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AddProductInCartDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.Service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

	@Autowired
	private CartService cartService;
	
	//add to cart	
	 @PostMapping(value = "/", produces = "application/json")
	    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto) {
	        try {
	            ResponseEntity<?> response = cartService.addProductToCart(addProductInCartDto);
	            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	        } catch (Exception e) {
	            // Log the exception for debugging purposes
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	        }
	    }
	 
	 //get all cart value of the current user
	 @GetMapping("/{userId}")
	 public ResponseEntity<?> getAllCartValueOfTheCurrentUsers(@PathVariable String userId)
	 {
		 OrderDto orderDto=this.cartService.getCartItemsByUserId(userId);
		 return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	 }
	 
	 //apply coupons
	 @GetMapping("/coupon/{userId}/{code}")
	 public ResponseEntity<?> applyCouponCode(@PathVariable String userId, @PathVariable String code)
	 {
		 try {
			 OrderDto orderDto=cartService.applyCoupon(userId, code);
			 return ResponseEntity.ok(orderDto);
		 }catch(ValidationException ex)
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		 }
	 }
}
