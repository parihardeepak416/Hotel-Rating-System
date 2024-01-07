package com.Gateway.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gateway.models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private Logger logger=LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/login") //hum redirect kar rhe he okta ke auth page par
//	okta ki screen chalegi but url ye wala chalega and ye respone hme milega jo hum yha se bhejenge
	public ResponseEntity<AuthResponse> login(
		@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
		@AuthenticationPrincipal OidcUser user,Model model)
	{
		//AuthResponse ka data aayega kha se to hume objects milenge 
//		ye objects hme okta provide karega.. "okta se hme info milegi "
		
		//user se authorities email mil jayega 
		//and client se hme expire at and access token mil jayega and hme authresponse banana he info se 
		
		logger.info("user email id "+user.getEmail());
		System.out.println("this is okta authentication");
		//creating and setting AuthResponse object
		AuthResponse authResponse=new AuthResponse();
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		authResponse.setUserId(user.getEmail());	
		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		//getAuthorities data is collection of GrantedAutority type so we change into string collection so we can pass to authresponse object
		List<String> authorities = user.getAuthorities().stream().map(grantedAuthority ->{
			return grantedAuthority.getAuthority();
		}).collect(Collectors.toList());
		authResponse.setAuthorities(authorities);
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
	}
//	@PostMapping("/test")
//	public void testing()
//	{
//		System.out.println("api gatewaytesting");
//	}
	
}