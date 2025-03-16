package com.restarurant.microservice_reservations.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping()
	public ResponseEntity<Object> getAllReservations(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(reservationService.getAllReservations());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getReservation(@PathVariable("id") Long id){
		try {
			Reservation reservation = reservationService.getReservation(id);
			return ResponseEntity.status(HttpStatus.OK).body(reservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getReservationByUserId(@PathVariable("id") Long id){
		try {
			Reservation reservation = reservationService.getReservationByUserId(id);
			return ResponseEntity.status(HttpStatus.OK).body(reservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@GetMapping("/table/{id}")
	public ResponseEntity<Object> getReservationByTableId(@PathVariable("id") Long id){
		try {
			Reservation reservation = reservationService.getReservationByTableId(id);
			return ResponseEntity.status(HttpStatus.OK).body(reservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@GetMapping("/date/{date}")
	public ResponseEntity<Object> getReservationByTableId(@PathVariable("id") LocalDateTime id){
		try {
			Reservation reservation = reservationService.getReservationByDate(id);
			return ResponseEntity.status(HttpStatus.OK).body(reservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createReservation(@RequestBody Reservation reservation){
		try {
			Reservation createReservation = reservationService.createReservation(reservation);
			return ResponseEntity.status(HttpStatus.OK).body(createReservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteReservation(@PathVariable Long id){
		try {
			reservationService.deleteReservation(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateReservation(@RequestBody Reservation reservation){
		try {
			Reservation updateReservation = reservationService.updateReservation(reservation);
			return ResponseEntity.status(HttpStatus.OK).body(updateReservation);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
