package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Kupac kupac;

    @OneToOne()
    private Restoran restoran;

    @Column(name = "Komentar", nullable = false)
    private String Komentar;

    @Column(name = "Ocena",nullable = false)
    private double Ocena;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Komentar(Kupac kupac, Restoran restoran, double ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        Ocena = ocena;
    }
    public Komentar(){}

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getKomentar() {
        return Komentar;
    }

    public void setKomentar(String komentar) {
        Komentar = komentar;
    }

    public double getOcena() {
        return Ocena;
    }

    public void setOcena(double ocena) {
        Ocena = ocena;
    }
}