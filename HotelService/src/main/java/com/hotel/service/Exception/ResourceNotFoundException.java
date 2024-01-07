package com.hotel.service.Exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException()
	{
		super("hotel is not found ");
	}
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
