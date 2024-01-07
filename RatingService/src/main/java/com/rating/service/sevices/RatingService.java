package com.rating.service.sevices;

import java.util.List;

import com.rating.service.entities.Rating;

public interface RatingService {

	//create 
	Rating createRating(Rating rating);
	
	//get all ratings 
	List<Rating> getAllRating();
	
	//get all rating of userId
	List<Rating> getRatingByUserId(String userId);
	
	//get all rating of hotelId
	List<Rating> getRatingByhotelId(String hotelId);
}
