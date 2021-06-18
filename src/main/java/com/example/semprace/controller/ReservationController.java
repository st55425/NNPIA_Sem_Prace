package com.example.semprace.controller;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.entity.Reservation;
import com.example.semprace.service.ReservationServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    private final ModelMapper modelMapper;

    @GetMapping("/reservations/anonym/{courtId}")
    public List<ReservationAnonymizedDto> getAnonymizedReservationByCourt(@PathVariable long courtId){
        return reservationService.findAnonymizedDtoByCourt(courtId).stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/reservations/future/{username}")
    public List<ReservationAnonymizedDto> getUserFutureReservations(@PathVariable String username){
        return reservationService.findFutureReservationsByUser(username).stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/reservations/past/{username}")
    public List<ReservationAnonymizedDto> getUserPastReservations(@PathVariable String username){
        return reservationService.findPastReservationsByUser(username).stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservationById(@PathVariable long id){
        System.out.println("test");
        reservationService.deleteReservation(id);
    }


    private ReservationAnonymizedDto convertToDto(Reservation reservation){
        return modelMapper.map(reservation, ReservationAnonymizedDto.class);
    }

    private Reservation convertToEntity(ReservationAnonymizedDto dto) throws ParseException {
        Reservation reservation = modelMapper.map(dto, Reservation.class);
        if (reservation.getId() != null){
            Reservation oldReservation = reservationService.fingById(reservation.getId());
            reservation.setUser(oldReservation.getUser());
            reservation.setReservable(oldReservation.getReservable());
        }
        return reservation;
    }

}
