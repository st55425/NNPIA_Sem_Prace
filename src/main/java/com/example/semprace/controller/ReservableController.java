package com.example.semprace.controller;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.service.ReservableServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ReservableController {

    private final ReservableServiceImpl service;

    @GetMapping("/courts")
    public List<CourtDto> getCourts(){
        return service.getAllCourts();
    }

    @GetMapping("/courts/{id}")
    public CourtDto getCourtById(@PathVariable long id){
        return service.getCourtById(id);
    }
}
