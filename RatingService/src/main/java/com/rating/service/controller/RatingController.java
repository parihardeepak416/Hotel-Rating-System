package com.rating.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entities.Rating;
import com.rating.service.sevices.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating)
	{
		Rating createRating = ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(createRating);
	}
	
	//get all
	@GetMapping
	public ResponseEntity<List<Rating>> getAll()
	{
		List<Rating> allRating = ratingService.getAllRating();
		return ResponseEntity.ok(allRating);
	}
	
	//get all by userId
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByuserId(@PathVariable String userId)
	{
		List<Rating> ratingByUserId = ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(ratingByUserId);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getratingsByhotelId(@PathVariable String hotelId)
	{
		List<Rating> ratingByhotelId = ratingService.getRatingByhotelId(hotelId);
		return ResponseEntity.ok(ratingByhotelId);
	}
	
}
