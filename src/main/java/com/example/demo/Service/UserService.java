package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.User;

public interface UserService {

	List<User> getAllUsers();

	User getUsersByUserId(String id);

	User createUsers(User user) throws Exception;

}
