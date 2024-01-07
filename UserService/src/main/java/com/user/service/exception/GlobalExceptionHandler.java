package com.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.service.payload.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	//this method runs when resource not found exception object thrown
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		//first we get message that ki message kya aaya he
		String message = ex.getMessage();
		//and then hum ye message ko ApiResponse ke andar message rakhenge and bhej denge
//		ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessageString(message);
		apiResponse.setSuccess(true);
		apiResponse.setStatus(HttpStatus.NOT_FOUND);	
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}

}
