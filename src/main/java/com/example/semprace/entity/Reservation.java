package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime timeFrom;

    @Column(nullable = false)
    private LocalDateTime timeTo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Reservable reservable;
}
