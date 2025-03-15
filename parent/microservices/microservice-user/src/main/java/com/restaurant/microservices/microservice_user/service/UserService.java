package com.restaurant.microservices.microservice_user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.microservices.microservice_user.model.User;
import com.restaurant.microservices.microservice_user.repository.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository	userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User with id " + id +
						" not found."));
	}

	@Override
	public User createUser(User user) {
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email is already in use.");
		}
		
		if(userRepository.existsByName(user.getName())) {
			throw new IllegalArgumentException("Name is already in use.");
		}
		
		user.setPassword(encodePassword(user.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {

		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("User with id " + id + " not found.");
		}
		
	}

	@Override
	public User updateUser(User user) {
		
		User userFromDb = userRepository.findById(user.getId())
				.orElseThrow(() -> new IllegalArgumentException("User with id " + 
		user.getId() + " not found."));
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email is already in use.");
		}
		
		if(userRepository.existsByName(user.getName())) {
			throw new IllegalArgumentException("Name is already in use.");
		}
		
		userFromDb.setEmail(user.getEmail());
		userFromDb.setName(user.getName());
		userFromDb.setPassword(encodePassword(user.getPassword()));
		
		return userRepository.save(userFromDb);
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
}
