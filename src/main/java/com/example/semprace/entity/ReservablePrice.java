package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;

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
    private Boolean clubMember;

    @Column(nullable = false)
    private Double timeFrom;

    @Column(nullable = false)
    private Double timeTo;

    @Column(nullable = false)
    private Double price;
}
