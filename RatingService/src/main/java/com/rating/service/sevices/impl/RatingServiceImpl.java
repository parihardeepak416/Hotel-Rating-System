package com.rating.service.sevices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.entities.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.sevices.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) {
		
		Rating saverating = ratingRepository.save(rating);
		return saverating;
	}

	@Override
	public List<Rating> getAllRating() {
		
		List<Rating> ratings= ratingRepository.findAll();
		return ratings;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		
		return ratingRepository.findByUserId(userId);
		
	}

	@Override
	public List<Rating> getRatingByhotelId(String hotelId) {
		
		return ratingRepository.findByHotelId(hotelId);
	}

	
}
