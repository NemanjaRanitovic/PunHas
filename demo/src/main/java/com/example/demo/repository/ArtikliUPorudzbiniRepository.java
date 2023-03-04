package com.example.demo.repository;

import com.example.demo.model.ArtikliUPorudzbini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikliUPorudzbiniRepository extends JpaRepository<ArtikliUPorudzbini,Long> {
}
