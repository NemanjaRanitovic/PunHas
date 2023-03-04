package com.example.demo.dto;

import com.example.demo.model.Korisnik;

import java.util.Date;

public class RegisterDto {
    private String username,password, name, lastName;
    private Korisnik.POLOVI pol;
    private Date datum;

    public RegisterDto(String username, String password, String Ime, String Prezime, Korisnik.POLOVI pol) {
        this.username = username;
        this.password = password;
        this.name = Ime;
        this.lastName = Prezime;
        this.pol = pol;
        this.datum = new java.util.Date(System.currentTimeMillis());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Korisnik.POLOVI getPol() {
        return this.pol;
    }

    public void setPol(Korisnik.POLOVI pol) {
        this.pol = pol;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
