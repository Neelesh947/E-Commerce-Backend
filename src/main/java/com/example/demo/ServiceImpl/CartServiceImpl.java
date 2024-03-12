package com.example.demo.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AddProductInCartDto;
import com.example.demo.Dto.CartItemsDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.Entity.CartItem;
import com.example.demo.Entity.Coupon;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderStatus;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CartItemRepository;
import com.example.demo.Repository.CouponRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	//add to cart items
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder=this.orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),OrderStatus.PENDING);		
		
		Optional<CartItem> optionalCartItems=this.cartItemRepository.findByProduct_ProductIdAndOrder_OrderIdAndUser_Id
				(addProductInCartDto.getProductId(), activeOrder.getOrderId(), addProductInCartDto.getUserId());
		
		if(optionalCartItems.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}else {
			Optional<Product> optionalProduct=this.productRepository.findById(addProductInCartDto.getProductId());
			Optional<User> optionalUser=this.userRepository.findById(addProductInCartDto.getUserId());
			
			if(optionalProduct.isPresent() && optionalUser.isPresent())
			{
				CartItem cart=new CartItem();
				cart.setProduct(optionalProduct.get());
				cart.setPrice(optionalProduct.get().getPrice());
				cart.setQuantity(1L);
				cart.setUser(optionalUser.get());
				cart.setOrder(activeOrder);
				
				String uuid=UUID.randomUUID().toString();
				cart.setCartId(uuid);						
				
				CartItem updatedCart=cartItemRepository.save(cart);
				
				activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cart.getPrice());
				activeOrder.setAmount(activeOrder.getAmount()+cart.getPrice());
				
				activeOrder.getCartItems().add(cart);
				this.orderRepository.save(activeOrder);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
			}
		}
	}
	
	//get all the cart items
	public OrderDto getCartItemsByUserId(String userId) {
		Order activeOrder=this.orderRepository.findByUserIdAndOrderStatus(userId,OrderStatus.PENDING);
		
		List<CartItemsDto>cartItemsDtosList=activeOrder.getCartItems().stream().map(CartItem::getCartDto).collect(Collectors.toList());
		
		OrderDto orderDto=new OrderDto();
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setOrderId(activeOrder.getOrderId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		orderDto.setCartItemsDto(cartItemsDtosList);
		
		if(activeOrder.getCoupon()!=null)
		{
			orderDto.setCouponName(activeOrder.getCoupon().getName());
		}
		
		return orderDto;
	} 
	
	//apply coupon code
	public OrderDto applyCoupon(String userId, String code) throws ValidationException
	{
		Order activeOrder=this.orderRepository.findByUserIdAndOrderStatus(userId,OrderStatus.PENDING);
		
		Coupon coupon=this.couponRepository.findByCode(code).orElseThrow(()-> new ValidationException("Coupons not found"));
		
		if(couponIsExpired(coupon))
		{
			throw new ValidationException("Coupon is Expired");
		}
		
		double discountAmount=((coupon.getDiscount()/100.0)* activeOrder.getTotalAmount());
		double netAmount=activeOrder.getTotalAmount()-discountAmount;
		
		activeOrder.setAmount((long)netAmount);
		activeOrder.setDiscount((long)discountAmount);
		activeOrder.setCoupon(coupon);
		
		orderRepository.save(activeOrder);
		return activeOrder.getOrderDto();
	}

	private boolean couponIsExpired(Coupon coupon)
	{
		Date currentDate=new Date();
		Date expirationDate=coupon.getExpirationDate();
		return expirationDate!=null && currentDate.after(expirationDate);
	}
}
