package com.example.semprace.service;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.repository.ReservationItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationItemRepository reservationItemRepository;


    public List<ReservationAnonymizedDto> getAnonymizedReservations(){
        return reservationItemRepository.getAnonymizedReservations().stream()
                .map(res -> new ReservationAnonymizedDto(res.getTimeFrom(), res.getTimeTo(), res.getReservable()))
                .collect(Collectors.toList());

    }
}
