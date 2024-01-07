package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.entities.Rating;
import com.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
	@Test
	void create()
	{
		Rating rating = new Rating();
		rating.setRating(10);
		rating.setUserId("");
	    rating.setHotelId("");
		rating.setFeedback("this is good hotel");
		Rating savedRating = ratingService.create(rating);
		System.out.println("rating is created");
	}

}
