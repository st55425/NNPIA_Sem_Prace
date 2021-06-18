package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private ZonedDateTime timeFrom;

    @Column(nullable = false)
    private ZonedDateTime timeTo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Reservable reservable;
}
