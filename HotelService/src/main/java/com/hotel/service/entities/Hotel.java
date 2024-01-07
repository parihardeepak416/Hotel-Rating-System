package com.hotel.service.entities;

import javax.persistence.*;
@Entity
public class Hotel {
	
	@Id
	private String hotelId;
	private String name;
	private String location;
	private String about;
	
	public String gethotelId() {
		return hotelId;
	}
	public void sethotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Hotel(String hotelId, String name, String location, String about) {
		super();
		this.hotelId = hotelId;
		this.name = name;
		this.location = location;
		this.about = about;
	}
	public Hotel() {
		super();
		
	}
	
}
