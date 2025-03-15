package com.restarurant.microservice_reservations.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.restarurant.microservice_reservations.dto.UserDto;

@FeignClient(name = "user-service",url = "http://localhost:8087/user")
public interface UserClient {
	@GetMapping("/{id}")
	Optional<UserDto> getUserById(@PathVariable("id") Long id);
}
