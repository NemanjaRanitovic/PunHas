package com.example.demo.repository;

import com.example.demo.model.Dostavljac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac, Long> {
    Dostavljac getByUsername(String username);
}