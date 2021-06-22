package com.example.semprace.service;

import com.example.semprace.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAnonymizedDtoByCourt(long courtId);

    Page<Reservation> findFutureReservationsByUser(String username, Pageable pageable);

    Page<Reservation> findPastReservationsByUser(String username, Pageable pageable);

    Reservation deleteReservation(long reservationId) throws Exception;

    Reservation saveReservation(Reservation reservation) throws Exception;
}
