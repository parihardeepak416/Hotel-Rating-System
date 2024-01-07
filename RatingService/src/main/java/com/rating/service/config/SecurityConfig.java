package com.rating.service.config;

import java.time.Year;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//isase hum controller ki methods par hi bta ske ki kon konsi service ko use kr ske
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception
	{
		//oauth configuratin
		security.authorizeHttpRequests()
		.anyRequest()
		.authenticated()
		.and()
		.oauth2ResourceServer()
		.jwt();
		//yhi pr hum authority bta skte he ki konsi api kon access kar paye
//		pr hum Ye enable prepost ki help karenge hasauthority ya preauthorize annotation ki help se kar lenge
		return security.build();
	}
}
