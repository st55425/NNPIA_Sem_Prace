package com.example.semprace.service;

import com.example.semprace.entity.Reservable;
import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.User;
import com.example.semprace.repository.ReservableRepository;
import com.example.semprace.repository.ReservationRepository;
import com.example.semprace.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    private final ReservableRepository reservableRepository;


    @Override
    public List<Reservation> findAnonymizedDtoByCourt(long courtId) {
        return reservationRepository.findAllByReservable(courtId);
    }

    @Override
    public Page<Reservation> findFutureReservationsByUser(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return reservationRepository.findAllByUserAndTimeFromAfterOrderByTimeFrom(user, ZonedDateTime.now(), pageable);
    }

    @Override
    public Page<Reservation> findPastReservationsByUser(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username);
        return reservationRepository.findAllByUserAndTimeFromBeforeOrderByTimeFrom(user, ZonedDateTime.now(), pageable);
    }

    @Override
    public Reservation deleteReservation(long reservationId) throws Exception {
        var reservation = reservationRepository.findById(reservationId).orElseThrow();
        if (reservation.getTimeFrom().compareTo(ZonedDateTime.now()) < 0) {
            throw new Exception("Proběhlou rezervaci nelze smazat");
        }
        reservationRepository.deleteById(reservationId);
        return reservation;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) throws Exception {
        long reservableId = reservation.getReservable().getId();
        String username = reservation.getUser().getUsername();
        Reservable r = reservableRepository.findById(reservableId).orElseThrow();
        User u = userRepository.findByUsername(username);
        userRepository.flush();
        reservation.setReservable(r);
        reservation.setUser(u);
        if (!validateReservation(reservation)) {
            throw new Exception("Data nejsou validní");
        }
        return reservationRepository.saveAndFlush(reservation);
    }

    private boolean validateReservation(Reservation reservation) {
        long resTimeDiff = reservation.getTimeFrom().compareTo(reservation.getTimeTo());
        long diffFromNow = reservation.getTimeFrom().compareTo(ZonedDateTime.now());
        if (resTimeDiff >= 0 || diffFromNow < 0) {
            return false;
        }
        var reservations = reservationRepository.findAllByReservableAndTimeFromBetweenAndTimeToBetween(
                reservation.getReservable(),
                reservation.getTimeFrom(), reservation.getTimeTo(),
                reservation.getTimeFrom(), reservation.getTimeTo());
        return reservations.isEmpty();
    }
}
