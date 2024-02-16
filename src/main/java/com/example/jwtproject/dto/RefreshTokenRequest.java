package com.example.jwtproject.dto;

public class RefreshTokenRequest {
	
	private String token;

	public RefreshTokenRequest(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
