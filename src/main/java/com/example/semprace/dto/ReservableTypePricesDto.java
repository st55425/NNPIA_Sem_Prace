package com.example.semprace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservableTypePricesDto {

    private Long id;

    private String name;

    private ZonedDateTime openFrom;

    private ZonedDateTime openTo;

    private List<ReservablePriceDto> prices;
}
