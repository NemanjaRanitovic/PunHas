package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac(Korisnik k, Set<Porudzbina>porudzbine){
        super(k);
        this.porudzbine = porudzbine;
    }
    public Dostavljac(String username, String password, String name, String lastName, Korisnik.POLOVI pol){
        super.username = username;
        super.password = password;
        super.name = name;
        super.lastName = lastName;
        super.Pol = pol;
        super.Datum_Kreiranja_naloga = new java.util.Date(System.currentTimeMillis());
        super.Uloga = ULOGA.Dostavljac;
    }
    public Dostavljac(){}

    public ULOGA getUloga(){
        return Uloga;
    }
    public String getUsername(){
        return username;
    }
}