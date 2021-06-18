package com.example.semprace.controller;

import com.example.semprace.dto.ReservableTypePricesDto;
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
        return reservableTypeService.findAllTypesWithPrices().stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    private ReservableTypePricesDto convertToDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypePricesDto.class);
    }

}
