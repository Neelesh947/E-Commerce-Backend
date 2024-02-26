package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User getUsersByUserId(String id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
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
			
			existUser=this.userRepository.save(user);
		}		
		return existUser;
	}
}