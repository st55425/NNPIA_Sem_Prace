package com.example.semprace.service;

import com.example.semprace.entity.ReservableType;
import com.example.semprace.repository.ReservableTypeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservableTypeServiceImpl {

    private final ReservableTypeRepository reservableTypeRepository;

    public List<ReservableType> findAllTypes(){
        return reservableTypeRepository.findAll();
    }

    public void deleteById(long id){
        reservableTypeRepository.deleteById(id);
    }

}
