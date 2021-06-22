package com.example.semprace.controller;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.dto.ReservationDto;
import com.example.semprace.entity.Reservation;
import com.example.semprace.service.ReservationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    private final ModelMapper modelMapper;

    @GetMapping("/reservations/anonym/{courtId}")
    public List<ReservationAnonymizedDto> getAnonymizedReservationByCourt(@PathVariable long courtId) {
        return reservationService.findAnonymizedDtoByCourt(courtId).stream().
                map(this::convertToDtoAnonymized).collect(Collectors.toList());
    }

    @PreAuthorize("#username == authentication.principal.username || hasAnyAuthority('STAFF', 'ADMIN')")
    @GetMapping(value = "/reservations/future/{username}", params = {"page", "size"})
    public Page<ReservationAnonymizedDto> getUserFutureReservations(@PathVariable String username,
                                                                    @RequestParam("page") int page,
                                                                    @RequestParam("size") int size) {
        return reservationService.findFutureReservationsByUser(username, PageRequest.of(page, size)).
                map(this::convertToDtoAnonymized);
    }

    @PreAuthorize("#username == authentication.principal.username || hasAnyAuthority('STAFF', 'ADMIN')")
    @GetMapping(value = "/reservations/past/{username}", params = {"page", "size"})
    public Page<ReservationAnonymizedDto> getUserPastReservations(@PathVariable("username") String username,
                                                                  @RequestParam("page") int page,
                                                                  @RequestParam("size") int size) {
        return reservationService.findPastReservationsByUser(username, PageRequest.of(page, size)).
                map(this::convertToDtoAnonymized);
    }

    @DeleteMapping("/reservations/{id}")
    public ReservationDto deleteReservationById(@PathVariable long id) throws Exception {
        return convertToDto(reservationService.deleteReservation(id));
    }

    @PostMapping("/reservations")
    public ReservationDto createReservation(@RequestBody ReservationDto reservationDto) throws Exception {
        return convertToDto(reservationService.saveReservation(convertToEntity(reservationDto)));
    }

    private ReservationDto convertToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    private ReservationAnonymizedDto convertToDtoAnonymized(Reservation reservation) {
        return modelMapper.map(reservation, ReservationAnonymizedDto.class);
    }

    private Reservation convertToEntity(ReservationDto dto) {
        var map = modelMapper.map(dto, Reservation.class);
        return map;
    }

}
