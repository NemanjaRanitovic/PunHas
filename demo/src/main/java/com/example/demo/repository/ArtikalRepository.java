package com.example.demo.repository;

import com.example.demo.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal,Long> {
    Artikal getByName(String naziv);
}