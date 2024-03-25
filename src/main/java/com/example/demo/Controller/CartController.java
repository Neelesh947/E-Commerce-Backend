package com.example.demo.Controller;

import java.util.List;

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
import com.example.demo.Dto.PlaceOrderDto;
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
	 
	 //add quantity/ increase quantity
	 @PostMapping("/addQuantity")
	 public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	 {
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseOrderQuantity(addProductInCartDto));
	 }
	 
	//Decrease quantity/ increase quantity
	 @PostMapping("/decreaseQuantity")
	 public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	 {
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseOrderQuantity(addProductInCartDto));
	 }
	 
	 //place order
	 @PostMapping("/place-order")
	 public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto)
	 {
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrderDto(placeOrderDto));
	 }
	 
	 //get the order for the customer/ user
	 @GetMapping("/myOrders/{userId}")
	 public ResponseEntity<List<OrderDto>> getPlacedOrder(@PathVariable String userId)
	 {
		 return ResponseEntity.ok(cartService.getMyPlacedOrder(userId));
	 }
}
