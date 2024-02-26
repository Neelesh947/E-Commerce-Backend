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

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> users=this.userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUsersByUserId(@PathVariable String id)
	{
		User users=this.userService.getUsersByUserId(id);
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping("/")
	public ResponseEntity<User> createNewUsers(@RequestBody User user) throws Exception
	{
		User users=this.userService.createUsers(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}
}
