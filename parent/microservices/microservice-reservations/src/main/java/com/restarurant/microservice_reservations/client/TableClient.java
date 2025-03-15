package com.restarurant.microservice_reservations.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.restarurant.microservice_reservations.dto.TableDto;

@FeignClient(name = "table-service",url = "http://localhost:8086")
public interface TableClient {
	@GetMapping("/table/id/{id}")
	Optional<TableDto> getTableById(@PathVariable Long id);
}
