package com.axis.jwtFilter;


import java.util.Arrays;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfigure {

	@Autowired
    AuthenticationProvider authenticationProvider;
    
    @Autowired
    JwtAuthenticateFilter jwtAuthenticateFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.cors()
		.and()
		.csrf().disable()
		
		.authorizeHttpRequests()
		.requestMatchers("/api/auth/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
         
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class)
		;
		
		
		
		
		return httpSecurity.build();
	}
	
//	 @Bean
//	  CorsConfigurationSource corsConfigurationSource() {
//	      CorsConfiguration configuration = new CorsConfiguration();
//	      configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//	      configuration.setAllowedMethods(Arrays.asList("GET"));
//	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	      source.registerCorsConfiguration("/api/**", configuration);
//	      return source;
//	  }
	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	  CorsConfiguration configuration = new CorsConfiguration();
	  configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	  configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE"));
	  //configuration.setAllowCredentials(true);
	  configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	 // configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
	 // configuration.setMaxAge(3600L);
	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  source.registerCorsConfiguration("/**", configuration);
	  return source;
	}

}
