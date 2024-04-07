package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, String>{

	Optional<WishList> findByUserAndProduct(User user, Product product);
	
	List<WishList> findAllByUserId(String userId);
}
