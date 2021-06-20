package com.example.semprace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private long id;

    private ZonedDateTime timeFrom;

    private ZonedDateTime timeTo;

    private long reservableId;

    private String userName;
}
