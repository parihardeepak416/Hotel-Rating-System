package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entities.Hotel;

public interface HotelService {

	//create
	Hotel createHotel(Hotel hotel);
	
	//getAll
	List<Hotel> getAllHotels();
	
	//get
	Hotel getHotel(String hotelId);
}
