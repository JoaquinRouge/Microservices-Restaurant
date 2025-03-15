package com.restarurant.microservice_reservations.service;

import java.time.LocalDateTime;
import java.util.List;

import com.restarurant.microservice_reservations.dto.UserDto;
import com.restarurant.microservice_reservations.model.Reservation;

public interface IReservationService {
	List<Reservation> getAllReservations();
	Reservation getReservation(Long id);
	Reservation getReservationByUserId(Long id);
	Reservation getReservationByTableId(Long id);
	Reservation getReservationByDate(LocalDateTime date);
	Reservation createReservation(Reservation reservation);
	void deleteReservation(Long id);
	Reservation updateReservation(Reservation reservation);
}
