package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.WishListDto;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Entity.WishList;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WishListRepository;
import com.example.demo.Service.WishListService;

@Service
public class WishListServiceImpl implements WishListService{

	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public WishListDto addProductToWishList(WishListDto wishListDto)
	{
		Optional<Product> optionalProduct=this.productRepository.findById(wishListDto.getProductId());
		Optional<User> optionalUser=this.userRepository.findById(wishListDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Optional<WishList> existingwishlist=this.wishListRepository.findByUserAndProduct(optionalUser.get(), optionalProduct.get());
			
			if(existingwishlist.isPresent())
			{
				 return null;
			}
			else
			{
				WishList wishlist=new WishList();
				
				wishlist.setProduct(optionalProduct.get());
				wishlist.setUser(optionalUser.get());
				
				return wishListRepository.save(wishlist).getWishListDto();
			}
		}
		return null;
	}
	
	public List<WishListDto> getWishListByUserId(String userId)
	{
		return this.wishListRepository.findAllByUserId(userId).stream().map(WishList::getWishListDto).collect(Collectors.toList());
	}
	
}
