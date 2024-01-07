package com.user.service.configuration.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class Interceptor implements RequestInterceptor {

	//by this interface we can get token , this interface are responsible for overall management of Authorized client
	@Autowired
	private OAuth2AuthorizedClientManager manager;
	
	@Override
	public void apply(RequestTemplate template) {
		
		
		//by this we are reauthorize using registration id 
		String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
		template.header("Authorization", "Bearer "+token);
	}

	
}
