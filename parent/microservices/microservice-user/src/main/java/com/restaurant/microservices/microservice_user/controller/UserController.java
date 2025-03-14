package com.restaurant.microservices.microservice_user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.microservices.microservice_user.model.User;
import com.restaurant.microservices.microservice_user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public IUserService userService;
	
	@GetMapping()
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id){
		try {
			User userFromDb = userService.getUser(id);
			return ResponseEntity.status(HttpStatus.OK).body(userFromDb);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user){
		try {
			User createUser = userService.createUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(createUser);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		try {
			userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		try {
			User updateUser = userService.updateUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
