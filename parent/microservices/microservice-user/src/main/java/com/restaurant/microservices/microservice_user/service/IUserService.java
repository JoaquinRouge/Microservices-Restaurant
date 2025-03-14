package com.restaurant.microservices.microservice_user.service;

import java.util.List;

import com.restaurant.microservices.microservice_user.model.User;

public interface IUserService {
	List<User> getAllUsers();
	User getUser(Long id);
	User createUser(User user);
	void deleteUser(Long id);
	User updateUser(User user);
}
