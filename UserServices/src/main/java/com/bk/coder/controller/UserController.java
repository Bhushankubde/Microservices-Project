package com.bk.coder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.coder.entities.User;
import com.bk.coder.services.UserServies;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServies userServies;
	
	//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User user1 = userServies.saveuser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	//getById
	@GetMapping("/{userId}")
	@CircuitBreaker(name="RatingHotelBreak",fallbackMethod="ratinghotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		
		User user = userServies.getUser(userId);
		return ResponseEntity.ok(user);
	}
	//creating fall back method in circuit breaker
	
	public ResponseEntity<User> ratinghotelFallback(String userId){
		 
		User user = new User("123","dummy","dummy@yahoo.com","This sevices are down", null);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	//getAll
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		
		List<User> allUsers = userServies.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	
}
