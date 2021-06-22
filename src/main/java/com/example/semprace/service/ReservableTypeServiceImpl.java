package com.example.semprace.service;

import com.example.semprace.entity.ReservableType;
import com.example.semprace.repository.ReservablePriceRepository;
import com.example.semprace.repository.ReservableTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservableTypeServiceImpl implements ReservableTypeService {

    private final ReservableTypeRepository reservableTypeRepository;

    private final ReservableService reservableService;

    private final ReservablePriceRepository reservablePriceRepository;

    @Override
    public List<ReservableType> findAllTypes(){
        return reservableTypeRepository.findAll();
    }

    @Override
    public ReservableType deleteById(long id){
        var reservableType = reservableTypeRepository.findById(id).orElseThrow();
        for (var res: reservableType.getReservableList()) {
            reservableService.deleteById(res.getId());
        }
        for (var price: reservableType.getPrices()) {
            reservablePriceRepository.deleteById(price.getId());
        }
        reservableTypeRepository.deleteById(id);
        return reservableType;
    }

    @Override
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

}
