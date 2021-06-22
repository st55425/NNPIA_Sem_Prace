package com.example.semprace.repository;

import com.example.semprace.entity.Reservable;
import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findAllByUserAndTimeFromAfterOrderByTimeFrom(User user, ZonedDateTime timeFrom, Pageable pageable);

    Page<Reservation> findAllByUserAndTimeFromBeforeOrderByTimeFrom(User user, ZonedDateTime timeFrom, Pageable pageable);

    @Query("""
    select res
    from Reservation res
    left join fetch res.reservable
    where res.reservable.id =:reservableId 
    """)
    List<Reservation> findAllByReservable(long reservableId);

    List<Reservation> findAllByReservableAndTimeFromBetweenAndTimeToBetween(Reservable reservable,
                                                                            ZonedDateTime fromTimeFrom, ZonedDateTime toTimeFrom,
                                                                            ZonedDateTime fromTimeTo, ZonedDateTime toTimeTo);
}
