package com.example.demo.repository;

import com.example.demo.model.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {
}