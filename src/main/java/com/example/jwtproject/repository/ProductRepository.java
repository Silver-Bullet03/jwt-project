package com.example.jwtproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwtproject.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	
}
