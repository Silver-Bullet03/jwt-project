package com.example.jwtproject.service;

import com.example.jwtproject.dto.JwtAuthenticationResponse;
import com.example.jwtproject.dto.RefreshTokenRequest;
import com.example.jwtproject.dto.SigninRequest;
import com.example.jwtproject.dto.SignupRequest;
import com.example.jwtproject.entities.User;

public interface AuthenticationService {
	
	User signup(SignupRequest signupRequest);
	
	JwtAuthenticationResponse signin(SigninRequest signinRequest);
	
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
