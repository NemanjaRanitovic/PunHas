package com.example.demo.dto;

import com.example.demo.model.Menadzer;

public class RestoranDto {
    private String naziv,tip;
    private double gSirina,gVisina;
    private String adresa;

    private boolean status;
    private String menadzer;

    public RestoranDto(String naziv, String tip, double gSirina, double gVisina, String adresa,String menadzer,boolean status) {
        this.naziv = naziv;
        this.tip = tip;
        this.gSirina = gSirina;
        this.gVisina = gVisina;
        this.adresa = adresa;
        this.menadzer = menadzer;
        this.status = status;
    }
    public RestoranDto(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getgSirina() {
        return gSirina;
    }

    public void setgSirina(double gSirina) {
        this.gSirina = gSirina;
    }

    public double getgVisina() {
        return gVisina;
    }

    public void setgVisina(double gVisina) {
        this.gVisina = gVisina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(String menadzer) {
        this.menadzer = menadzer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
