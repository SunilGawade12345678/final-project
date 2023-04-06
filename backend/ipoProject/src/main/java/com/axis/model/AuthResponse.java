package com.axis.model;

public class AuthResponse {
	private String token;
	
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public AuthResponse(String token) {
		super();
		this.token = token;
//		this.username = username;
//		this.role = role;
	}


//
//	public String getUsername() {
//		return username;
//	}
//
//
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//
//
//	public Role getRole() {
//		return role;
//	}
//
//
//
//	public void setRole(Role role) {
//		this.role = role;
//	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
