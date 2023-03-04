package com.example.demo.service;

import com.example.demo.model.Artikal;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public List<Artikal> pronadjiSve(){ return artikalRepository.findAll(); }
}
