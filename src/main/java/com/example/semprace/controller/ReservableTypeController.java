package com.example.semprace.controller;

import com.example.semprace.dto.ReservableTypePricesDto;
import com.example.semprace.dto.ReservableTypesCourtsDto;
import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.entity.ReservableType;
import com.example.semprace.entity.Reservation;
import com.example.semprace.service.ReservableTypeServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservableTypeController {

    private final ReservableTypeServiceImpl reservableTypeService;

    private final ModelMapper modelMapper;

    @GetMapping("/reservabletypes/prices")
    public List<ReservableTypePricesDto> findAllTypesWithPrices(){
        return reservableTypeService.findAllTypes().stream().
                map(this::convertToPricesDto).collect(Collectors.toList());
    }

    @GetMapping("/reservabletypes/courts")
    public List<ReservableTypesCourtsDto> findAllTypesWithCourts(){
        return reservableTypeService.findAllTypes().stream().
                map(this::convertToCourtsDto).collect(Collectors.toList());
    }

    private ReservableTypePricesDto convertToPricesDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypePricesDto.class);
    }

    private ReservableTypesCourtsDto convertToCourtsDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypesCourtsDto.class);
    }
}
