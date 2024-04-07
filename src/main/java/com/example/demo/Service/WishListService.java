package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.WishListDto;

public interface WishListService {

	public WishListDto addProductToWishList(WishListDto wishListDto);
	
	public List<WishListDto> getWishListByUserId(String userId);
}
