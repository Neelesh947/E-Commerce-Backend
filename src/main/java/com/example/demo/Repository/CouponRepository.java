package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String>{

	//get the existing coupons
	boolean existsByCode(String code);
}
