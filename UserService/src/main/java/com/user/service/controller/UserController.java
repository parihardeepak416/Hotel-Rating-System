package com.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entities.User;
import com.user.service.services.UserService;

import ch.qos.logback.classic.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//so this method runs when /users url passed by postmapping 
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	int retryCount=0;
	//api which calls hotel and rating service
	//single user get
	//so using path variable url userid is passed into method userId and we should put same spelling of userId
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//	@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod ="ratingHotelFallback" )
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		retryCount++;
		System.out.println("retry count= "+retryCount);
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//fallback method
	public ResponseEntity<User>ratingHotelFallback(String userId,Exception ex)
	{
		System.out.println("fallback is executed because service is down "+ex.getMessage());
		User user=new User();
		user.setName("deepak");
		user.setAbout("this user is dummy because service is down");
		user.setEmail("parihardeepak416@gmail.com");
		user.setUserId("13");
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	//for all users
	@GetMapping
	public ResponseEntity<List<User>>getAllUsers()
	{
		List<User> allUser = userService.getAllUser();
		System.out.println("this is all users ");
		return ResponseEntity.ok(allUser);
	}
}