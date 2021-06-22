package com.example.semprace.service;

import com.example.semprace.entity.Reservable;

import java.util.List;

public interface ReservableService {
    List<Reservable> getAllCourts();

    Reservable getCourtById(long id);

    List<Reservable> getCourtsByType(long typeId);

    Reservable deleteById(long id);

    Reservable saveReservable(Reservable reservable, Long reservableTypeId) throws Exception;
}
