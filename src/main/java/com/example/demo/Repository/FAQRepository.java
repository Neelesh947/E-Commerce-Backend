package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {

	 List<FAQ> findAllByProductProductId(String productId);
}
