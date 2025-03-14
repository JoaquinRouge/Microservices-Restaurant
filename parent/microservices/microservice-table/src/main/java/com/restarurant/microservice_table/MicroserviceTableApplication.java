package com.restarurant.microservice_table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTableApplication.class, args);
	}

}
