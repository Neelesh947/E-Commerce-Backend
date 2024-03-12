package com.example.demo.ServiceImpl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Coupon;
import com.example.demo.Repository.CouponRepository;
import com.example.demo.Service.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponRepository couponRepository;
	
	//create coupons	
	public Coupon createCoupon(Coupon coupon) throws ValidationException
	{
		if(couponRepository.existsByCode(coupon.getCode()))
		{
			throw new ValidationException("coupon code already exists");
		}
		return couponRepository.save(coupon);
	}
	
	//get all coupons
	public List<Coupon> getAllCoupons()
	{
		return this.couponRepository.findAll();
	}
}
