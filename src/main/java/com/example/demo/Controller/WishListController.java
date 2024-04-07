package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.WishListDto;
import com.example.demo.Service.WishListService;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("*")
public class WishListController {

	@Autowired
	private WishListService wishListService;
	
	@PostMapping()
	public ResponseEntity<?> addProductToWishList(@RequestBody WishListDto wishListDto)
	{
		WishListDto wishList=this.wishListService.addProductToWishList(wishListDto);
		if(wishList==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(wishList);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<WishListDto>> getWishListByUserId(@PathVariable String userId)
	{
		return ResponseEntity.ok(this.wishListService.getWishListByUserId(userId));
	}
}
