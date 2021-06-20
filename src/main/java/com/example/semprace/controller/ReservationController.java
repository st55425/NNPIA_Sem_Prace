package com.example.semprace.controller;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.dto.ReservationDto;
import com.example.semprace.entity.Reservation;
import com.example.semprace.service.ReservationServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

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
                map(this::convertToDtoAnonymized).collect(Collectors.toList());
    }

    @GetMapping("/reservations/future/{username}")
    public List<ReservationAnonymizedDto> getUserFutureReservations(@PathVariable String username){
        return reservationService.findFutureReservationsByUser(username).stream().
                map(this::convertToDtoAnonymized).collect(Collectors.toList());
    }

    @GetMapping("/reservations/past/{username}")
    public List<ReservationAnonymizedDto> getUserPastReservations(@PathVariable String username){
        return reservationService.findPastReservationsByUser(username).stream().
                map(this::convertToDtoAnonymized).collect(Collectors.toList());
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservationById(@PathVariable long id){
        reservationService.deleteReservation(id);
    }

    @PostMapping("/reservations")
    public ReservationDto createReservation(@RequestBody ReservationDto reservationDto) throws Exception {
        return convertToDto(reservationService.saveReservation(convertToEntity(reservationDto)));
    }

    private ReservationDto convertToDto(Reservation reservation){
        return modelMapper.map(reservation, ReservationDto.class);
    }

    private ReservationAnonymizedDto convertToDtoAnonymized(Reservation reservation){
        return modelMapper.map(reservation, ReservationAnonymizedDto.class);
    }

    private Reservation convertToEntity(ReservationDto dto) {
        var map = modelMapper.map(dto, Reservation.class);
        return map;
    }

}
