package com.example.semprace.service;

import com.example.semprace.entity.ReservableType;
import com.example.semprace.repository.ReservablePriceRepository;
import com.example.semprace.repository.ReservableTypeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservableTypeServiceImpl {

    private final ReservableTypeRepository reservableTypeRepository;

    private final ReservableServiceImpl reservableService;

    private final ReservablePriceRepository reservablePriceRepository;

    public List<ReservableType> findAllTypes(){
        return reservableTypeRepository.findAll();
    }

    public void deleteById(long id){
        var reservableType = reservableTypeRepository.findById(id).orElseThrow();
        for (var res: reservableType.getReservableList()) {
            reservableService.deleteById(res.getId());
        }
        for (var price: reservableType.getPrices()) {
            reservablePriceRepository.deleteById(price.getId());
        }
        reservableTypeRepository.deleteById(id);
    }

    public ReservableType saveReservableType(ReservableType reservableType) throws Exception {
        if (validateInput(reservableType)){
            ReservableType resType =  reservableTypeRepository.saveAndFlush(reservableType);
            for (var price: reservableType.getPrices()) {
                price.setType(resType);
                reservablePriceRepository.saveAndFlush(price);
            }
            return resType;
        }
        throw new Exception("Data nejsou validní");
    }

    private boolean validateInput(ReservableType reservableType){
        if (reservableType.getOpenFrom().compareTo(reservableType.getOpenTo()) >=0){
            return false;
        }
        for (var price: reservableType.getPrices()) {
            long timeDiff = price.getTimeFrom().compareTo(price.getTimeTo());
            long openFromDiff = price.getTimeFrom().compareTo(reservableType.getOpenFrom());
            long openToDiff = price.getTimeTo().compareTo(reservableType.getOpenTo());
            if (timeDiff >= 0 || openFromDiff < 0 || openToDiff >0){
                return false;
            }
        }
        return true;
    }
}
