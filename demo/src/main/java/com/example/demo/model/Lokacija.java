package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {
    @Column(name = "GeoSirina", nullable = false)
    private double gSirina;
    @Column(name = "GeoVisina",nullable = false)
    private double gVisina;
    @Column(name = "Adresa", nullable = false)
    private String Adresa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Lokacija(double geoSirina,double geoVisina,String adresa) {
        this.gSirina = geoSirina;
        this.gVisina = geoVisina;
        this.Adresa = adresa;
    }
    public Lokacija(){}

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
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }
}