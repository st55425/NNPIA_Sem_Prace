package com.example.semprace.controller;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation")
    public List<ReservationAnonymizedDto> getAnonymizedReservation(){
        return reservationService.getAnonymizedReservations();
    }

}
