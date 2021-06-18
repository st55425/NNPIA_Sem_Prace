package com.example.semprace.service;

import com.example.semprace.entity.ReservablePrice;
import com.example.semprace.entity.ReservableType;
import com.example.semprace.repository.ReservablePriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservablePriceServiceImpl {

    private final ReservablePriceRepository reservablePriceRepository;

    public List<ReservablePrice> findAllPrices(){
        return reservablePriceRepository.findAll();
    }
}
