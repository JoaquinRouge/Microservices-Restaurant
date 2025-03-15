package com.restarurant.microservice_reservations.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restarurant.microservice_reservations.model.Reservation;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long>{
	Optional<Reservation> findByUserId(Long id);	
	Optional<Reservation> findByTableId(Long id);	
	Optional<Reservation> findByDate(LocalDateTime date);	
	Optional<Reservation> findByTableIdAndDate(Long tableId,LocalDateTime date);
}
