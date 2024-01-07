package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;



public interface UserService {

	//create user
	User saveUser(User user);
	
	//get all users
	List<User> getAllUser();
	
	//get single user using given id
	User getUser(String userId);
	
	
	
}
