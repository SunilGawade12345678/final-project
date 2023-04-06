package com.axis.jwtFilter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.axis.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {
    
	@Autowired
	JwtService jwtService;
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(
		 	HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String  authHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken=authHeader.substring(7);
		  
		username=jwtService.extractUsername(jwtToken);
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
     if(jwtService.isTokenValid(jwtToken, userDetails)) {
    	 UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
						userDetails,
						null, userDetails.getAuthorities());
				
    	 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
    	 // updating SecurityContextHolder with new request
    	 SecurityContextHolder.getContext().setAuthentication(authToken);
						
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
