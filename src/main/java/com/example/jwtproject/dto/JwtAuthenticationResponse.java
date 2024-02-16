package com.example.jwtproject.dto;

import com.example.jwtproject.entities.Role;

public class JwtAuthenticationResponse {

	private String token;
	private String refreshToken;
	private String name;
	private String email;
	private Role role;
	private long id;
	
	
	
	public JwtAuthenticationResponse(String token, String refreshToken, String name, String email, Role role, long id) {
		super();
		this.token = token;
		this.refreshToken = refreshToken;
		this.name = name;
		this.email = email;
		this.role = role;
		this.id = id;
	}

	public JwtAuthenticationResponse() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
}
