package com.example.demo.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Admin extends Korisnik {
    public Admin(Korisnik k){
        super(k);
    }
    public Admin(String username,String password,String name,String LastName,POLOVI pol, Date datum,ULOGA uloga){
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = LastName;
        this.Pol = pol;
        this.Datum_Kreiranja_naloga = datum;
        this.Uloga = uloga;
    }
    public Admin(){
        super();
    }

    public String getUsername(){
        return username;
    }
    public ULOGA getUloga(){
        return Uloga;
    }

}