package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.Dto.CartItemsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class CartItem {

	@Id
	private String cartId;
	private Long price;
	private Long quantity;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	public CartItemsDto getCartDto() {
		CartItemsDto cartItemsDto=new CartItemsDto();
		cartItemsDto.setCartId(cartId);
		cartItemsDto.setPrice(price);
		cartItemsDto.setProductId(product.getProductId());
		cartItemsDto.setQuantity(quantity);
		cartItemsDto.setUserId(user.getId());
		cartItemsDto.setProductName(product.getName());
		cartItemsDto.setReturnedImg(product.getImg());
		return cartItemsDto;
	}
}
