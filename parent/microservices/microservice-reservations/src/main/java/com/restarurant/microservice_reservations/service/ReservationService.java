package com.restarurant.microservice_reservations.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restarurant.microservice_reservations.client.TableClient;
import com.restarurant.microservice_reservations.client.UserClient;
import com.restarurant.microservice_reservations.dto.UserDto;
import com.restarurant.microservice_reservations.model.Reservation;
import com.restarurant.microservice_reservations.repository.IReservationRepository;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private IReservationRepository reservationRepository;
	@Autowired
	private UserClient userClient;
	@Autowired
	private TableClient tableClient;
	
	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation getReservation(Long id) {
		return reservationRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Reservation with id "
							+ id + " not found."));
	}

	@Override
	public Reservation getReservationByUserId(Long id) {
		return reservationRepository.findByUserId(id)
				.orElseThrow(() -> new IllegalArgumentException(
						"Reservation for user with id " + id + " not found."));
	}

	@Override
	public Reservation getReservationByTableId(Long id) {
		return reservationRepository.findByTableId(id)
				.orElseThrow(() -> new IllegalArgumentException(
						"Reservation for table with id " + id + " not found."));
	}

	@Override
	public Reservation getReservationByDate(LocalDateTime date) {
		return reservationRepository.findByDate(date)
				.orElseThrow(() -> new IllegalArgumentException(
						"Reservation for date " + date + " not found."));
	}

	@Override
    public Reservation createReservation(Reservation reservation) {
        // Validar que la fecha no sea en el pasado
        if (reservation.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No puedes reservar en una fecha pasada.");
        }

        // Validar que la mesa existe
        if (!tableClient.getTableById(reservation.getTableId()).isPresent()) {
            throw new IllegalArgumentException("La mesa con ID " + reservation.getTableId() + " no existe.");
        }

        // Validar que el usuario existe
        if (!userClient.getUserById(reservation.getUserId()).isPresent()) {
            throw new IllegalArgumentException("El usuario con ID " + reservation.getUserId() + " no existe.");
        }

        // Validar que la mesa no esté reservada en el mismo horario
        Optional<Reservation> existingReservation = reservationRepository.findByTableIdAndDate(reservation.getTableId(), reservation.getDate());
        if (existingReservation.isPresent()) {
            throw new IllegalArgumentException("Esta mesa ya está reservada en este horario.");
        }

        // Guardar la reserva si pasa todas las validaciones
        return reservationRepository.save(reservation);
    }


	@Override
	public void deleteReservation(Long id) {
		if(reservationRepository.existsById(id)) {
			reservationRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("Reservation with id " 
						+ id + " not found.");
		}

	}

	@Override
	public Reservation updateReservation(Reservation reservation) {
		
		Reservation reservationFromDb = this.getReservation(reservation.getId());
		
        if (reservation.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No puedes reservar en una fecha pasada.");
        }
		
        if (!userClient.getUserById(reservation.getUserId()).isPresent()) {
            throw new IllegalArgumentException("El usuario con ID " + reservation.getUserId() + " no existe.");
        }

        Optional<Reservation> existingReservation = reservationRepository.findByTableIdAndDate(reservation.getTableId(), reservation.getDate());
        if (existingReservation.isPresent()) {
            throw new IllegalArgumentException("Esta mesa ya está reservada en este horario.");
        }
        
        reservationFromDb.setDate(reservation.getDate());
        reservationFromDb.setTableId(reservation.getTableId());
        reservationFromDb.setUserId(reservation.getUserId());
        
		return reservationRepository.save(reservationFromDb);
	}
	
}
