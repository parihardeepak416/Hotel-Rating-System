package com.hotel.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.Exception.ResourceNotFoundException;
import com.hotel.service.entities.Hotel;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		
		hotelRepository.save(hotel);
		return hotel;
	}

	@Override
	public List<Hotel> getAllHotels() {
		
		List<Hotel> hotels = hotelRepository.findAll();
		return hotels;
	}

	@Override
	public Hotel getHotel(String hotelId) {
		
		Hotel hotel = hotelRepository.findById(hotelId)
		.orElseThrow(() -> new ResourceNotFoundException("hotel is not found "+hotelId));
		return hotel;
	}

}
