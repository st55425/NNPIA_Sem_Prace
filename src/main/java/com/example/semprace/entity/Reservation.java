package com.example.semprace.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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
