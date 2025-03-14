package com.restarurant.microservice_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceNotificationsApplication.class, args);
	}

}
