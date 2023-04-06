package com.axis.service;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtService {

	public static final String  SECRECT="25432A462D4A614E645267556B58703272357538782F413F4428472B4B625065";
	
	public String extractUsername(String jwtToken) {
		// TODO Auto-generated method stub
		return extractClaim(jwtToken, Claims::getSubject);
	}
    
	public <T> T extractClaim(String jwtToken , Function<Claims, T> claimResolver) {
		Claims claims=	extractAllClaims(jwtToken);
		return claimResolver.apply(claims);
		
	}
	
	 private Claims extractAllClaims(String jwtToken ) {
		 
		 return Jwts
				 .parserBuilder()
				 .setSigningKey(getSignInKey())
				 .build()
				 .parseClaimsJws(jwtToken)
				 .getBody();
	 }
	
	 
	 // generate token using only username
	public String generateToken(UserDetails userDetails) {
		
	//	Map<String, Object > claims= new HashMap<>();
		
		return createToken( new HashMap<>(), userDetails);
		
	}
	
	private String createToken( Map<String, Object > claims , UserDetails userDetails ) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	// validate generated token
	
	public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
		String username= extractUsername(jwtToken);
		return (username.equals(userDetails.getUsername())) && !isExpireToken(jwtToken);
	}
	
	
	private boolean isExpireToken(String jwtToken) {
		// TODO Auto-generated method stub
		return  extractExpiration(jwtToken).before(new Date());
	}

	private Date extractExpiration(String jwtToken) {
		// TODO Auto-generated method stub
		return extractClaim(jwtToken, Claims::getExpiration);
	}

	//getting signature of token
	private Key getSignInKey() {
		// TODO Auto-generated method stub
		
		byte[] keyByte= Decoders.BASE64.decode(SECRECT);
		return Keys.hmacShaKeyFor(keyByte);
	}

}
