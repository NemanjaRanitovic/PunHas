package com.example.demo.dto;

public class LokacijaDto {
    public String adresa;
    public double gVisina;
    public double gSirina;

    public LokacijaDto(String adresa, double gVisina, double gSirina) {
        this.adresa = adresa;
        this.gVisina = gVisina;
        this.gSirina = gSirina;
    }
    public LokacijaDto(){}

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getgVisina() {
        return gVisina;
    }

    public void setgVisina(double gVisina) {
        this.gVisina = gVisina;
    }

    public double getgSirina() {
        return gSirina;
    }

    public void setgSirina(double gSirina) {
        this.gSirina = gSirina;
    }
}
