package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Artikal {
    public Artikal(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Tip {Jelo,Pice}
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Tip",nullable = false)
    private Tip Tip;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "Restoran_id")
    private Restoran restoran;

    @Column()
    private String name;

    @Column()
    private double Cena;


    @Column()
    private double Kolicina;

    @Column(name = "Opis")
    private String Opis;

    public Artikal(Artikal.Tip tip, String naziv, double cena, double kolicina,Restoran restoran) {
        Tip = tip;
        this.name = naziv;
        Cena = cena;
        Kolicina = kolicina;
        this.restoran = restoran;
    }

    public Artikal(Artikal.Tip tip, String naziv, double cena) {
        Tip = tip;
        this.name = naziv;
        Cena = cena;
    }

    public Artikal.Tip getTip() {
        return Tip;
    }

    public void setTip(Artikal.Tip tip) {
        Tip = tip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCena() {
        return Cena;
    }

    public void setCena(double cena) {
        Cena = cena;
    }

    public double getKolicina() {
        return Kolicina;
    }

    public void setKolicina(double kolicina) {
        Kolicina = kolicina;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public Long getId() {
        return id;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public void setId(Long id) {
        this.id = id;
    }
}