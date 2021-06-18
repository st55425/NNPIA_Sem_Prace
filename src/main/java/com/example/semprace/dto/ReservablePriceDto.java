package com.example.semprace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservablePriceDto {

    private Long id;

    private Boolean weekDays;

    private Boolean weekendsAndHolidays;

    private ZonedDateTime timeFrom;

    private ZonedDateTime timeTo;

    private BigDecimal price;
}
