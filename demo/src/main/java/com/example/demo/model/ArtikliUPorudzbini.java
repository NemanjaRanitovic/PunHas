package com.example.demo.model;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

import static javax.persistence.EnumType.ORDINAL;

@Entity
public class ArtikliUPorudzbini implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    private Artikal artikal;

    @Column
    private int broj;
    public ArtikliUPorudzbini(){}

    public ArtikliUPorudzbini(Artikal artikal, int broj) {
        this.artikal = artikal;
        this.broj = broj;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }
}
