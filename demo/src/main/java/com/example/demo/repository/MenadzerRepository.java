package com.example.demo.repository;

import com.example.demo.model.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer,Long> {
    Menadzer getByUsername(String username);
}