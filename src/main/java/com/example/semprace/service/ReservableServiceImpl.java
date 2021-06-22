package com.example.semprace.service;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.repository.ReservableRepository;
import com.example.semprace.repository.ReservableTypeRepository;
import com.example.semprace.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservableServiceImpl implements ReservableService {

    private final ReservableRepository reservableRepository;

    private final ReservationRepository reservationRepository;

    private final ReservableTypeRepository reservableTypeRepository;

    @Override
    public List<Reservable> getAllCourts() {
        return reservableRepository.findAllCourts();
    }

    @Override
    public Reservable getCourtById(long id) {
        return reservableRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Reservable> getCourtsByType(long typeId) {
        var type = reservableTypeRepository.findById(typeId).orElseThrow();
        return reservableRepository.findAllByReservableType(type);
    }

    @Override
    public Reservable deleteById(long id) {
        var reservable = reservableRepository.findById(id).orElseThrow();
        var reservations = reservationRepository.findAllByReservable(id);
        reservationRepository.deleteAll(reservations);
        reservableRepository.deleteById(id);
        return reservable;
    }

    @Override
    public Reservable saveReservable(Reservable reservable, Long reservableTypeId) throws Exception {
        var resType = reservableTypeRepository.findById(reservableTypeId).orElseThrow();
        if (reservable.getName().length() > 0) {
            reservable.setReservableType(resType);
            return reservableRepository.saveAndFlush(reservable);
        }

        throw new Exception("Nevalidní data");
    }
}
