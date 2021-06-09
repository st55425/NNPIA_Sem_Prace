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

    public List<CourtDto> getAllCourts(){
        return reservableRepository.findAllCourts().stream().
                map(res -> new CourtDto(res.getId(), res.getName())).collect(Collectors.toList());
    }

    public CourtDto getCourtById(long id){
        Reservable res = reservableRepository.findById(id).orElseThrow();
        return new CourtDto(res.getId(), res.getName());
    }
}
