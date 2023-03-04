package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String Naziv;
    @Column
    private String tip;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Artikal> Meni = new HashSet<>();
    @OneToOne(cascade=CascadeType.ALL)
    private Lokacija lokacija;

    @Column
    private boolean radi;


    public Restoran(String naziv, String tip, Set<Artikal> meni, Lokacija lokacija) {
        Naziv = naziv;
        this.tip = tip;
        Meni = meni;
        this.lokacija = lokacija;
    }
    public Restoran(String naziv,String tip){
        this.Naziv = naziv;
        this.tip = tip;
    }
    public Restoran(String naziv,String adresa,String tip,double gSirina,double gVisina,boolean radi){
        this.Naziv = naziv;
        this.lokacija = new Lokacija(gSirina,gVisina,adresa);
        this.tip = tip;
        this.radi = radi;

    }

    public Restoran(){}

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        Naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Set<Artikal> getMeni() {
        return Meni;
    }

    public void setMeni(Set<Artikal> meni) {
        Meni = meni;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public boolean isRadi() {
        return radi;
    }

    public void setRadi(boolean radi) {
        this.radi = radi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getgVisina(){return this.lokacija.getgVisina();}

    public double getgSirina(){return this.lokacija.getgSirina();}

    public String getAdresa(){return this.lokacija.getAdresa();}

}