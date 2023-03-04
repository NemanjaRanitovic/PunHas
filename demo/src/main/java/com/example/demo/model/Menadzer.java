package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne(cascade= CascadeType.ALL)
    private Restoran restoran;
    public Menadzer(String username, String password,String name, String lastName, POLOVI pol){
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.Pol = pol;
        this.Datum_Kreiranja_naloga = new java.util.Date(System.currentTimeMillis());
        this.Uloga = ULOGA.Menadzer;
    }


    public Menadzer(Korisnik k, Restoran zadRest){
        super(k);
        this.restoran = zadRest;
    }
    public Menadzer(){}

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getUsername(){
        return username;
    }
    public String getRole(){
        String uloga;
        uloga = Uloga.toString();  //Izmena za SS
        return uloga;
    }
}