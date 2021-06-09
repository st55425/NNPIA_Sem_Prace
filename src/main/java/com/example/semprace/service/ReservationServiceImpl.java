package com.example.semprace.service;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl {

    private final ReservationRepository reservationRepository;


    public List<ReservationAnonymizedDto> findAnonymizedDtoByCourt(long courtId){
        return reservationRepository.findAllByReservable(courtId).stream().
                map(res -> new ReservationAnonymizedDto(res.getTimeFrom(), res.getTimeTo(), res.getReservable().getId())).
                collect(Collectors.toList());
    }
}
