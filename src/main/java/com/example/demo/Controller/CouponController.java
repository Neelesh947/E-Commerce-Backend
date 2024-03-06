package com.example.demo.Controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Coupon;
import com.example.demo.Service.CouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
@CrossOrigin("*")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@PostMapping("/")
	public ResponseEntity<Coupon> createNewCoupons(@RequestBody Coupon coupon) throws ValidationException
	{
		Coupon newcoupon=this.couponService.createCoupon(coupon);
		return ResponseEntity.status(HttpStatus.CREATED).body(newcoupon);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Coupon>> getAllCoupons()
	{
		List<Coupon> lcoupon=this.couponService.getAllCoupons();
		return ResponseEntity.status(HttpStatus.OK).body(lcoupon);
	}
}
