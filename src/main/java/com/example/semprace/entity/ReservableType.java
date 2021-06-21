package com.example.semprace.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(nullable = false)
    private BigDecimal defaultPrice;

    @OneToMany(mappedBy = "reservableType", fetch = FetchType.LAZY)
    private List<Reservable> reservableList;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<ReservablePrice> prices;
}
