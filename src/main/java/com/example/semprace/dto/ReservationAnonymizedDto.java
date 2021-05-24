package com.example.semprace.dto;

import com.example.semprace.entity.Reservable;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ReservationAnonymizedDto {

    private LocalDateTime timeFrom;

    private LocalDateTime timeTo;

    private Reservable reservable;
}
