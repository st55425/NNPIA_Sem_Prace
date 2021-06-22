package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

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
    @Basic
    @Temporal(TemporalType.TIME)
    private Date timeFrom;

    @Column(nullable = false)
    @Basic
    @Temporal(TemporalType.TIME)
    private Date timeTo;

    @Column(nullable = false)
    private BigDecimal price;
}
