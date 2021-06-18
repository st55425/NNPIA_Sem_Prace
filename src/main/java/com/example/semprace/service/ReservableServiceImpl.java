package com.example.semprace.service;

import com.example.semprace.dto.CourtDto;
import com.example.semprace.entity.Reservable;
import com.example.semprace.repository.ReservableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReservableServiceImpl {

    private final ReservableRepository reservableRepository;

    public List<Reservable> getAllCourts(){
        return reservableRepository.findAllCourts();
    }

    public Reservable getCourtById(long id){
        return reservableRepository.findById(id).orElseThrow();
    }
}
