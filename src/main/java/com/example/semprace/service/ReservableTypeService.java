package com.example.semprace.service;

import com.example.semprace.entity.ReservableType;

import java.util.List;

public interface ReservableTypeService {
    List<ReservableType> findAllTypes();

    ReservableType deleteById(long id);

    ReservableType saveReservableType(ReservableType reservableType) throws Exception;

    default boolean validateInput(ReservableType reservableType) {
        if (reservableType.getOpenFrom().compareTo(reservableType.getOpenTo()) >= 0) {
            return false;
        }
        for (var price : reservableType.getPrices()) {
            long timeDiff = price.getTimeFrom().compareTo(price.getTimeTo());
            long openFromDiff = price.getTimeFrom().compareTo(reservableType.getOpenFrom());
            long openToDiff = price.getTimeTo().compareTo(reservableType.getOpenTo());
            if (timeDiff >= 0 || openFromDiff < 0 || openToDiff > 0) {
                return false;
            }
        }
        return true;
    }
}
