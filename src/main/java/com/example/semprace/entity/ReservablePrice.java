package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
public class ReservablePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ReservableType type;

    @Column(nullable = false)
    private Boolean weekDays;

    @Column(nullable = false)
    private Boolean weekendsAndHolidays;

    @Column(nullable = false)
    private ZonedDateTime timeFrom;

    @Column(nullable = false)
    private ZonedDateTime timeTo;

    @Column(nullable = false)
    private BigDecimal price;
}
