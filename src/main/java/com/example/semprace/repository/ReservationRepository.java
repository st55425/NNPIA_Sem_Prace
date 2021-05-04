package com.example.semprace.repository;

import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUser(User user);
}
