package com.example.jwtproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtproject.entities.Product;
import com.example.jwtproject.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	
	private ProductRepository productRepository;
	
	@GetMapping
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hello! Admin");
	}

	@PostMapping("/addProduct")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
}
