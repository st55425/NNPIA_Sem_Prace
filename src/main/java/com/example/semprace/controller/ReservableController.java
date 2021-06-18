package com.example.semprace.controller;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.service.ReservableServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservableController {

    private final ReservableServiceImpl service;

    private final ModelMapper modelMapper;

    @GetMapping("/courts")
    public List<CourtDto> getCourts(){

        List<Reservable> courts = service.getAllCourts();
        return courts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/courts/{id}")
    public CourtDto getCourtById(@PathVariable long id){
        return convertToDto(service.getCourtById(id));
    }

    private CourtDto convertToDto(Reservable reservable){
        return modelMapper.map(reservable, CourtDto.class);
    }

    private Reservable convertToEntity(CourtDto dto) throws ParseException {
        Reservable reservable = modelMapper.map(dto, Reservable.class);
        if (reservable.getId() != null){
            Reservable oldReservable = service.getCourtById(reservable.getId());
            reservable.setReservableType(oldReservable.getReservableType());
            reservable.setReservations(oldReservable.getReservations());
            reservable.setAvailable(oldReservable.isAvailable());
        }
        return reservable;
    }
}
