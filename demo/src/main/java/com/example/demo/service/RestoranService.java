package com.example.demo.service;

import com.example.demo.model.Restoran;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {

    @Autowired
    RestoranRepository restoranRepository;

    public List<Restoran> pronadjiSve(){ return restoranRepository.findAll(); }

    public Optional<Restoran> nadjiPoId(long id){
        Optional<Restoran> restoran = restoranRepository.findById(id);
        if(restoran.isPresent()){
            return restoran;
        }
        return null;
    }
}
