package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.Dto.WishListDto;

import lombok.Data;

@Entity
@Data
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wishId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;	

	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public WishListDto getWishListDto()
	{
		WishListDto wishListDto=new WishListDto();
		wishListDto.setWishId(wishId);
		wishListDto.setProductId(product.getProductId());
		wishListDto.setProductName(product.getName());
		wishListDto.setProductDescription(product.getDescription());
		wishListDto.setPrice(product.getPrice());
		wishListDto.setUserId(user.getId());
		wishListDto.setReturnedImg(product.getImg());
		
		return wishListDto;
	}
}
