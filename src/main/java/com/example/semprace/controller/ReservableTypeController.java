package com.example.semprace.controller;

import com.example.semprace.dto.ReservableTypeDto;
import com.example.semprace.dto.ReservableTypePricesDto;
import com.example.semprace.dto.ReservableTypeCourtsDto;
import com.example.semprace.entity.ReservableType;
import com.example.semprace.service.ReservableTypeServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

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
    public List<ReservableTypeCourtsDto> findAllTypesWithCourts(){
        return reservableTypeService.findAllTypes().stream().
                map(this::convertToCourtsDto).collect(Collectors.toList());
    }
    @GetMapping("/reservabletypes")
    public List<ReservableTypeDto> getAllReservableTypesById(){
        return reservableTypeService.findAllTypes().stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/reservabletypes/{id}")
    public void deleteReservableTypeById(@PathVariable long id){
        reservableTypeService.deleteById(id);
    }

    private ReservableTypePricesDto convertToPricesDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypePricesDto.class);
    }

    private ReservableTypeCourtsDto convertToCourtsDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypeCourtsDto.class);
    }

    private ReservableTypeDto convertToDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypeDto.class);
    }
}
