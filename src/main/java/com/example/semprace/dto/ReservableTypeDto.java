package com.example.semprace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservableTypeDto {

    private Long id;

    private String name;

    private ZonedDateTime openFrom;

    private ZonedDateTime openTo;
}
