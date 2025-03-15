package com.restarurant.microservice_reservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restarurant.microservice_reservations.model.Reservation;
import com.restarurant.microservice_reservations.service.IReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private IReservationService reservationService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createReservation(@RequestBody Reservation reservation){
		try {
			Reservation createReservation = reservationService.createReservation(reservation);
			return ResponseEntity.status(HttpStatus.OK).body(createReservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
