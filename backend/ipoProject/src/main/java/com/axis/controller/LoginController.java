package com.axis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.DTO.UserDTO;
import com.axis.exception.InfoNotSavedException;
import com.axis.model.AuthResponse;
import com.axis.model.AuthenticationRequest;
import com.axis.service.AuthenticateService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	AuthenticateService authenticateService;
	
    @PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserDTO userDto ) throws InfoNotSavedException{
    
    	try {
    		String token=authenticateService.registerUser(userDto);
        	return ResponseEntity.ok(new AuthResponse(token)) ;
		} catch (Exception e) {
			throw new InfoNotSavedException("register not saved");
		}
    	
	}
    
    @PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthenticationRequest request ) throws Exception
    {   
    	try {
    		String token=authenticateService.authenticateUser(request);
    	    
        	return ResponseEntity.ok(new AuthResponse(token)) ;
			
		} catch (Exception e) {
			throw new UsernameNotFoundException(request.getUsername());
		
		}
	}
}
