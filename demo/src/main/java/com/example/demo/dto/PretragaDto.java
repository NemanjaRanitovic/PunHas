package com.example.demo.dto;

import com.example.demo.repository.PorudzbinaRepository;

public class PretragaDto {
/*
    private String ime;
    private String adresa;
    private double gVisina;
    private double gSirina;
    private String tip;

    public PretragaDto(String ime, String adresa, double gVisina, double gSirina, String tip) {
        this.ime = ime;
        this.adresa = adresa;
        this.gVisina = gVisina;
        this.gSirina = gSirina;
        this.tip = tip;
    }

    public PretragaDto(){}

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

 */

    private String parametri;

    public PretragaDto(String parametri) {

        this.parametri = parametri;
    }

    private PretragaDto(){}

    public String getParametri() {

        return parametri;
    }

    public void setParametri(String parametri) {
        this.parametri = parametri;
    }
}
