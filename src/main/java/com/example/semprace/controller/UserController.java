package com.example.semprace.controller;

import com.example.semprace.dto.UserDto;
import com.example.semprace.entity.User;
import com.example.semprace.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<UserDto> getUsers(){
        return userService.getUsers().stream().
                map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
