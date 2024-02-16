package com.example.jwtproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.jwtproject.entities.Role;
import com.example.jwtproject.entities.User;
import com.example.jwtproject.repository.UserRepository;

@SpringBootApplication
public class JwtProjectApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtProjectApplication.class, args);
	}

	@Override
	public void run(String... args){
		
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		
		if(adminAccount == null) {
			User user = new User();
			
			user.setEmail("admin@gmail.com");
			user.setName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			
			userRepository.save(user);
		}
		
	}

}
