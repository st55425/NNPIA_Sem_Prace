package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
public class ReservableType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, /*unique = true,*/ length = 30)
    private String name;

    @Column(nullable = false)
    private boolean isCourt;

    @Column(nullable = false)
    private ZonedDateTime openFrom;

    @Column(nullable = false)
    private ZonedDateTime openTo;

    @OneToMany(mappedBy = "reservableType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reservable> reservableList;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReservablePrice> prices;
}
