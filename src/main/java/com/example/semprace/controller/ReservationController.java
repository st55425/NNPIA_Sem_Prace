package com.example.semprace.controller;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.service.ReservationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @GetMapping("/reservations/{courtId}")
    public List<ReservationAnonymizedDto> getAnonymizedReservationByCourt(@PathVariable long courtId){
        return reservationService.findAnonymizedDtoByCourt(courtId);
    }

}
