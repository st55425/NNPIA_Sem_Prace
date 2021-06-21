package com.example.semprace.service;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.repository.ReservableRepository;
import com.example.semprace.repository.ReservableTypeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservableServiceImpl {

    private final ReservableRepository reservableRepository;

    private final ReservableTypeRepository reservableTypeRepository;

    public List<Reservable> getAllCourts() {
        return reservableRepository.findAllCourts();
    }

    public Reservable getCourtById(long id) {
        return reservableRepository.findById(id).orElseThrow();
    }

    public List<Reservable> getCourtsByType(long typeId) {
        var type = reservableTypeRepository.findById(typeId).orElseThrow();
        return reservableRepository.findAllByReservableType(type);
    }

    public void deleteById(long id) {
        reservableRepository.deleteById(id);
    }

    public Reservable saveReservable(Reservable reservable, Long reservableId) throws Exception {
        var resType = reservableTypeRepository.findById(reservableId).orElseThrow();
        if (reservable.getName().length() > 0) {
            reservable.setReservableType(resType);
            return reservableRepository.saveAndFlush(reservable);
        }

        throw new Exception("Nevalidní data");
    }
}
