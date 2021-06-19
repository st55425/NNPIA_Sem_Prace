package com.example.semprace.repository;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.entity.ReservableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReservableRepository extends JpaRepository<Reservable, Long> {

    @Query("""
    select res
    from Reservable res
    left join fetch res.reservableType
    where res.available = true and res.reservableType.isCourt = true
    """)
    List<Reservable> findAllCourts();

    List<Reservable> findAllByReservableType(ReservableType type);

}
