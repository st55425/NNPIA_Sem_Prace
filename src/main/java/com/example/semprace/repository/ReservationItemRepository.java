package com.example.semprace.repository;


import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {

    List<ReservationItem> findAllByReservation(Reservation reservation);

    List<ReservationItem> findAllByTimeFromBetween(LocalDateTime from, LocalDateTime to);

    @Query("""
        select resItem
        from ReservationItem resItem
        left join fetch resItem.reservable res
        left join fetch res.reservableType resType
        where resType.isCourt = true
    """)
    List<ReservationItem> getAnonymizedReservations();
}
