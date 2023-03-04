package com.example.demo.dto;

import com.example.demo.model.Artikal;

public class ArtikalIzmenaDto {
    private String naziv;
    private String noviNaziv;
    private double cena;
    private Artikal.Tip tip;

    public ArtikalIzmenaDto(String naziv, String noviNaziv, double cena, Artikal.Tip tip) {
        this.naziv = naziv;
        this.noviNaziv = noviNaziv;
        this.cena = cena;
        this.tip = tip;
    }
    public  ArtikalIzmenaDto(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNoviNaziv() {
        return noviNaziv;
    }

    public void setNoviNaziv(String noviNaziv) {
        this.noviNaziv = noviNaziv;
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
