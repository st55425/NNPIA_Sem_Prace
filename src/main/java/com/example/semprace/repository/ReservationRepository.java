package com.example.semprace.repository;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserAndTimeFromAfterOrderByTimeFrom(User user, ZonedDateTime timeFrom);

    List<Reservation> findAllByUserAndTimeFromBeforeOrderByTimeFrom(User user, ZonedDateTime timeFrom);

    @Query("""
    select res
    from Reservation res
    left join fetch res.reservable
    where res.reservable.id =:reservableId 
    """)
    List<Reservation> findAllByReservable(long reservableId);
}
