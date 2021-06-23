package com.example.semprace.controller;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.service.ReservableService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(value = "/api/courts")
public class ReservableController {

    private final ReservableService service;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<CourtDto> getCourts(){
        List<Reservable> courts = service.getAllCourts();
        return courts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping()
    public CourtDto insertCourt(@RequestBody CourtDto court) throws Exception {
        return convertToDto(service.saveReservable(convertToEntity(court), court.getReservableTypeId()));
    }

    @PutMapping()
    public CourtDto UpdateCourt(@RequestBody CourtDto court) throws Exception {
        return convertToDto(service.saveReservable(convertToEntity(court), court.getReservableTypeId()));
    }

    @GetMapping("/{id}")
    public CourtDto getCourtById(@PathVariable long id){
        return convertToDto(service.getCourtById(id));
    }

    @GetMapping("/types/{typeId}")
    public List<CourtDto>getCourtsByType(@PathVariable long typeId){
        List<Reservable> courts = service.getCourtsByType(typeId);
        return courts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public CourtDto deleteCourtById(@PathVariable long id){
        return convertToDto(service.deleteById(id));
    }

    private CourtDto convertToDto(Reservable reservable){
        return modelMapper.map(reservable, CourtDto.class);
    }

    private Reservable convertToEntity(CourtDto dto) {
        //return modelMapper.map(dto, Reservable.class); z nejakyho duvodu nefunguje
        var res = new Reservable();
        res.setId(dto.getId());
        res.setAvailable(dto.isAvailable());
        res.setName(dto.getName());
        return res;
    }
}
