package com.example.semprace.controller;

import com.example.semprace.dto.ReservationAnonymizedDto;
import com.example.semprace.dto.ReservationDto;
import com.example.semprace.dto.UserDto;
import com.example.semprace.entity.Reservation;
import com.example.semprace.entity.User;
import com.example.semprace.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserServiceImpl userService;

    private final ModelMapper modelMapper;

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return userService.getUsers().stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
