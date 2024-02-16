package com.example.jwtproject.service.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtproject.dto.JwtAuthenticationResponse;
import com.example.jwtproject.dto.RefreshTokenRequest;
import com.example.jwtproject.dto.SigninRequest;
import com.example.jwtproject.dto.SignupRequest;
import com.example.jwtproject.entities.Role;
import com.example.jwtproject.entities.User;
import com.example.jwtproject.repository.UserRepository;
import com.example.jwtproject.service.AuthenticationService;
import com.example.jwtproject.service.JWTService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;
	
	public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	public User signup(SignupRequest signupRequest) {
		
		User user = new User();
		
		user.setName(signupRequest.getName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setRole(Role.USER);
		
		return userRepository.save(user);
	}
	
	public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
				signinRequest.getPassword()));
		
		var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		jwtAuthenticationResponse.setName(user.getName());
		jwtAuthenticationResponse.setEmail(user.getEmail());
		jwtAuthenticationResponse.setRole(user.getRole());
		
		return jwtAuthenticationResponse;
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
			var jwt = jwtService.generateToken(user);
			
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			jwtAuthenticationResponse.setName(user.getName());
			jwtAuthenticationResponse.setEmail(user.getEmail());
			jwtAuthenticationResponse.setRole(user.getRole());
			jwtAuthenticationResponse.setId(user.getId());
			
			return jwtAuthenticationResponse;
		}
		
		return null;
	}
	
}
