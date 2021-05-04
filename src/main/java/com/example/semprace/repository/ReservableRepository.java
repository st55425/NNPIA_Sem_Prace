package com.example.semprace.repository;

import com.example.semprace.entity.Reservable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservableRepository extends JpaRepository<Reservable, Long> {

}
