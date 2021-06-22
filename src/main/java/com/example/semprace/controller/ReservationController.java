package com.example.semprace.controller;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.dto.ReservationDto;
import com.example.semprace.entity.Reservation;
import com.example.semprace.service.ReservationServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("#username == authentication.principal.username || hasAnyAuthority('STAFF', 'ADMIN')")
    @GetMapping("/reservations/future/{username}")
    public List<ReservationAnonymizedDto> getUserFutureReservations(@PathVariable String username){
        return reservationService.findFutureReservationsByUser(username).stream().
                map(this::convertToDtoAnonymized).collect(Collectors.toList());
    }

    @PreAuthorize("#username == authentication.principal.username || hasAnyAuthority('STAFF', 'ADMIN')")
    @GetMapping("/reservations/past/{username}")
    public List<ReservationAnonymizedDto> getUserPastReservations(@PathVariable("username") String username){
        return reservationService.findPastReservationsByUser(username).stream().
                map(this::convertToDtoAnonymized).collect(Collectors.toList());
    }

    @DeleteMapping("/reservations/{id}")
    public ReservationDto deleteReservationById(@PathVariable long id) throws Exception {
        return convertToDto(reservationService.deleteReservation(id));
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
