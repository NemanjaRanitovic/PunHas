package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {
    @Id
    private UUID UUID = java.util.UUID.randomUUID();


    @ManyToOne(fetch = FetchType.EAGER)
    private Restoran restoranIKP; //iz kog je poruceno

    @Column(name = "Datum")
    private Date datum; //vreme je sadrzano u datumu pa ga ne moramo importovati.

    @Column(name = "Cena")
    private double Cena;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Kupac Kupac;

    public enum STATUS {Porucivanje,Obrada,Priprema,CekaDostavu,uTransportu, Dostavljena,Otkazana}
    @Enumerated(EnumType.ORDINAL)

    @Column(name = "Status")
    private STATUS Status;
    @Column(name = "UCena")
    private double Ucena;

    @Column
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<ArtikliUPorudzbini> listaArtikalaUPorudzbini = new HashSet<>();
/*
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Artikal> listaArtikala = new HashSet<>();
    */

    /*
    public Porudzbina(java.util.UUID UUID, Restoran restoranIKP, Date datum, double cena, Kupac kupac, STATUS status, Set<Artikal> listaArtikala) {
        this.UUID = UUID;
        this.restoranIKP = restoranIKP;
        this.datum = datum;
        Cena = cena;
        Kupac = kupac;
        Status = status;
        this.listaArtikala = listaArtikala;
    }*/

    public Porudzbina( Restoran restoranIKP, Date datum, Kupac kupac, STATUS status, Set<ArtikliUPorudzbini> listaArtikala) {
        this.restoranIKP = restoranIKP;
        this.datum = datum;
        Kupac = kupac;
        Status = status;
        this.listaArtikalaUPorudzbini = listaArtikala;
        // dodavanje cene u zav. od artikala
        for(ArtikliUPorudzbini artikal : listaArtikala) {
            this.Cena += artikal.getArtikal().getCena();
        }
    }
    public Porudzbina(){}
    public java.util.UUID getUUID() {
        return UUID;
    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public Restoran getRestoranIKP() {
        return restoranIKP;
    }

    public void setRestoranIKP(Restoran restoranIKP) {
        this.restoranIKP = restoranIKP;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getUCena(){
        double uCena = 0;
        for(ArtikliUPorudzbini artikal : listaArtikalaUPorudzbini) {
             uCena += artikal.getArtikal().getCena() * artikal.getBroj();
            this.Cena += uCena;
        }
        this.Ucena = uCena;
        return uCena;
    }

    public double getCena() {
        return Cena;
    }

    public void setCena(double cena) {
        Cena = cena;
    }

    public Kupac getKupac() {
        return Kupac;
    }

    public void setKupac(Kupac kupac) {
        Kupac = kupac;
    }

    public STATUS getStatus() {
        return Status;
    }

    public void setStatus(STATUS status) {
        Status = status;
    }

    /*
    public Set<Artikal> getListaArtikala() {
        return listaArtikala;
    }

    public void setListaArtikala(Set<Artikal> listaArtikala) {

        this.listaArtikala = listaArtikala;
    }
*/

    public Set<ArtikliUPorudzbini> getListaArtikalaUPorudzbini() {
        return listaArtikalaUPorudzbini;
    }

    public void setListaArtikalaUPorudzbini(Set<ArtikliUPorudzbini> listaArtikalaUPorudzbini) {
        this.listaArtikalaUPorudzbini = listaArtikalaUPorudzbini;
    }

}