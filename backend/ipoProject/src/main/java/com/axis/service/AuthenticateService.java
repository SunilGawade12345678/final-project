package com.axis.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axis.DTO.UserDTO;
import com.axis.model.AuthenticationRequest;
import com.axis.model.Role;
import com.axis.model.User;
import com.axis.repo.UserRepo;

@Service
public class AuthenticateService {

	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
     @Autowired
	JwtService jwtService;
     
     
     
     public String registerUser(UserDTO userDTO) {
 		
 		User user = new User();
 		user.setName(userDTO.getName());
 		
 		user.setUsername(userDTO.getUsername());
 		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
 		user.setRole(userDTO.getRole());
 		
 		userRepo.save(user);
 UserDetails  userDetails =userDetailsService.loadUserByUsername(user.getUsername());
 		
 		String token=jwtService.generateToken(userDetails);
 		
 		return token;
 	   
 	}
     
     
     public String authenticateUser(AuthenticationRequest request) throws Exception {
  	   
 		authenticate(request.getUsername(), request.getPassword() );
 		 
 		UserDetails  userDetails =userDetailsService.loadUserByUsername(request.getUsername());
 		System.out.println("user+++"+userDetails.getUsername());
 		
 		String token=jwtService.generateToken(userDetails);
 		
 		return token ;
 	}
   // authenticate username and paassword
 	private void authenticate(String username, String password) throws Exception {
 		Objects.requireNonNull(username);
 		Objects.requireNonNull(password);
 		try {
 			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
 		} catch (DisabledException e) {
 			throw new Exception("USER_DISABLED", e);
 		} catch (BadCredentialsException e) {
 			throw new Exception("INVALID_CREDENTIALS", e);
 		}
 	}
     
     
     
}
