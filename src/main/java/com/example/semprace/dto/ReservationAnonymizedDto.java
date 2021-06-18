package com.example.semprace.dto;

import lombok.*;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationAnonymizedDto {

    private long id;

    private ZonedDateTime timeFrom;

    private ZonedDateTime timeTo;

    private String reservableName;
}
