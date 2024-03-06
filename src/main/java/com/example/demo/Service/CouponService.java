package com.example.demo.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.example.demo.Entity.Coupon;

public interface CouponService {

	public Coupon createCoupon(Coupon coupon) throws ValidationException;
	
	public List<Coupon> getAllCoupons();
}
