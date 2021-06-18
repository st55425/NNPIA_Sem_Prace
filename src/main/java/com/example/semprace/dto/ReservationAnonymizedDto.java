package com.example.semprace.dto;

import com.example.semprace.entity.Reservable;
import lombok.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ReservationAnonymizedDto {

    private long id;

    private ZonedDateTime timeFrom;

    private ZonedDateTime timeTo;

    private String reservableName;
}
