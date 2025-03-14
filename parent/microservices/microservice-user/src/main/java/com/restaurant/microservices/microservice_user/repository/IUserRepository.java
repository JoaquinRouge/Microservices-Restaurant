package com.restaurant.microservices.microservice_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.microservices.microservice_user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	boolean existsByEmail(String email);
	boolean existsByName(String name);
}
