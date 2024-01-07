package com.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randuserId = UUID.randomUUID().toString();
		user.setUserId(randuserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found !! "+userId));
		
//		rest Template
//		we are fetching rating of the above user from the rating service
//		ArrayList forObject = restTemplate.getForObject("http://localhost:8081/rating/users/7", ArrayList.class);
//		this data is not converted into arraylist class and it converts into linked hash map so we get errors that Rating is unnamed 
//		means class cast exception so we do rating array for solution
//		ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8081/rating/users/"+user.getUserId(), ArrayList.class);
     	Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
		//System.out.println(rating.toString());
		List<Rating> list = Arrays.stream(ratingOfUser).toList();
		logger.info("{} ",ratingOfUser);
		
		List<Rating> ratinglistwithHotel =list.stream().map(rating -> {
			//fetching hotel
		//	ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			//HttpStatus statusCode = forEntity.getStatusCode();
		//	System.out.println("status code"+statusCode);
		//	Hotel hotel = forEntity.getBody();
		//	rating.setHotel(hotel);
			
			//doing feign client coding
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			
			return rating;
		}).collect(Collectors.toList());	
		
		user.setRatings(ratinglistwithHotel);		
		return user;
				
				
	}

}
