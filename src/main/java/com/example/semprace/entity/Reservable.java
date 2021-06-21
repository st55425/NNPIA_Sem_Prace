package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Reservable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, /*unique = true,*/ length = 50)
    private String name;

    @ManyToOne(optional = false)
    private ReservableType reservableType;

    @Column(nullable = false)
    private boolean available;

    @OneToMany(mappedBy = "reservable", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
