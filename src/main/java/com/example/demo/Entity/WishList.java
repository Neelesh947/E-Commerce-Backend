package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
public class WishList {

	@Id
	private String wishId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;	

	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
}
