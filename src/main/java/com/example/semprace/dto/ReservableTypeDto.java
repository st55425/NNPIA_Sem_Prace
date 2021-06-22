package com.example.semprace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservableTypeDto {

    private Long id;

    private String name;

    private Date openFrom;

    private Date openTo;

    private List<ReservablePriceDto> prices;

    private List<CourtDto> reservableList;

    private BigDecimal defaultPrice;
}
