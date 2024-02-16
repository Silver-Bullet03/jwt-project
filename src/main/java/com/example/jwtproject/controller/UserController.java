package com.example.jwtproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtproject.entities.Product;
import com.example.jwtproject.exception.ResourceNotFoundException;
import com.example.jwtproject.repository.ProductRepository;
import com.example.jwtproject.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private UserRepository userRepository;
	private ProductRepository productRepository;
	
	@GetMapping
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hello! User!");
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/allProducts")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("/user/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exists with id: " + id));
		return ResponseEntity.ok(product);
	}
	
}
