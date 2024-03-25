package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

	Order findByUserIdAndOrderStatus(String userId, OrderStatus orderStatus);
	
	List<Order> findAllByOrderStatusIn(List<OrderStatus> getOrderStatus);
	
	List<Order> findByUserIdAndOrderStatusIn(String userId, List<OrderStatus> orderStatus);
}
