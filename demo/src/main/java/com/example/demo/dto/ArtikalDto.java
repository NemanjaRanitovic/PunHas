package com.example.demo.dto;

import com.example.demo.model.Artikal;

public class ArtikalDto {
    private String naziv;
    private double cena;
    private Artikal.Tip tip;
    // private Slika slika dodacu posle

    public ArtikalDto(String naziv, double cena, Artikal.Tip tip) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
    }
    public ArtikalDto(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Artikal.Tip getTip() {
        return tip;
    }

    public void setTip(Artikal.Tip tip) {
        this.tip = tip;
    }
}
