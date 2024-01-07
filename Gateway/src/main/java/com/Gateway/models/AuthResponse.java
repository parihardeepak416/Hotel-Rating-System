package com.Gateway.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	//hum yha se jp bhejnege vo yha likhenge
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String>authorities;
	
}
