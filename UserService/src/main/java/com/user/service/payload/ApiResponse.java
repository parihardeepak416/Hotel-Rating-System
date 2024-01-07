package com.user.service.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {

	private String messageString;
	private boolean success;
	private HttpStatus status;
	public String getMessageString() {
		return messageString;
	}
	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public ApiResponse(String messageString, boolean success, HttpStatus status) {
		super();
		this.messageString = messageString;
		this.success = success;
		this.status = status;
	}
	public ApiResponse() {
		super();
		
	}
	
	
}
