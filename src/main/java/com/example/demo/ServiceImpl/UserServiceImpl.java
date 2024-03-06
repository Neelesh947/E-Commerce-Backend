package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderStatus;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User getUsersByUserId(String id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
	//create users	
	public User createUsers(User user) throws Exception {
		
		
		User existUser=this.userRepository.findByUsername(user.getUsername());
		if(existUser != null)
		{
			System.out.println("User already present");
			throw new Exception("USER_ALREADY_PRESENT");
		}else {
			String uuid=UUID.randomUUID().toString();
			user.setId(uuid);
			
			if(user.getRole()==null)
			{
				user.setRole(UserRole.CUSTOMER);
			}
			
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));			
			String str1=user.getFirstname();
			String str2=user.getLastname();
			String str3= str1+" "+str2;
			user.setFullname(str3);
			
			Order order=new Order();
			String orderuuid=UUID.randomUUID().toString();
			order.setOrderId(orderuuid);
			order.setAmount(0L);
			order.setTotalAmount(0L);
			order.setDiscount(0L);
			order.setUser(user);
			order.setOrderStatus(OrderStatus.PENDING);
			orderRepository.save(order);
			
			existUser=this.userRepository.save(user);
		}		
		return existUser;
	}
}