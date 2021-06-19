package com.example.semprace.service;

import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.User;
import com.example.semprace.repository.ReservationRepository;
import com.example.semprace.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;


    public List<Reservation> findAnonymizedDtoByCourt(long courtId){
        return reservationRepository.findAllByReservable(courtId);
    }

    public List<Reservation> findFutureReservationsByUser(String username){
        User user = userRepository.findByUsername(username);
        return reservationRepository.findAllByUserAndTimeFromAfterOrderByTimeFrom(user, ZonedDateTime.now());
    }

    public List<Reservation> findPastReservationsByUser(String username){
        User user = userRepository.findByUsername(username);
        return reservationRepository.findAllByUserAndTimeFromBeforeOrderByTimeFrom(user, ZonedDateTime.now());
    }

    public Reservation fingById(long reservationId){
        return reservationRepository.findById(reservationId).orElseThrow();
    }

    public void deleteReservation(long reservationId){
        reservationRepository.deleteById(reservationId);
    }
}
