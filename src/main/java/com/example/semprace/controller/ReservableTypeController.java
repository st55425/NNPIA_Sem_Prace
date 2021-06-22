package com.example.semprace.controller;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.dto.ReservableTypeDto;
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
    @GetMapping("/reservabletypes")
    public List<ReservableTypeDto> getAllReservableTypes(){
        return reservableTypeService.findAllTypes().stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/reservabletypes")
    public ReservableTypeDto insertCourt(@RequestBody ReservableTypeDto reservableType) throws Exception {
        return convertToDto(reservableTypeService.saveReservableType(convertToEntity(reservableType)));
    }

    @PutMapping("/reservabletypes")
    public ReservableTypeDto updateCourt(@RequestBody ReservableTypeDto reservableType) throws Exception {
        return convertToDto(reservableTypeService.saveReservableType(convertToEntity(reservableType)));
    }

    @DeleteMapping("/reservabletypes/{id}")
    public ReservableTypeDto deleteReservableTypeById(@PathVariable long id){
        return convertToDto(reservableTypeService.deleteById(id));
    }

    private ReservableTypeDto convertToDto(ReservableType reservableType){
        return modelMapper.map(reservableType, ReservableTypeDto.class);
    }

    private ReservableType convertToEntity(ReservableTypeDto dto){
        return modelMapper.map(dto, ReservableType.class);
    }
}
