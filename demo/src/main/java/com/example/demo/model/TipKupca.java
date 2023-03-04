package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime", nullable = false)
    private String Ime;

    @Column(name = "Popust", nullable = false)
    private int TrazeniBrb;

    public TipKupca(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public int getTrazeniBrb() {
        return TrazeniBrb;
    }

    public void setTrazeniBrb(int trazeniBrb) {
        TrazeniBrb = trazeniBrb;
    }
}