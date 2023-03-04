package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private ArtikliUPorudzbiniRepository artikliUPorudzbiniRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public Kupac register(String username, String password, String name, String lastName, Korisnik.POLOVI pol) {
        Kupac kupac = new Kupac(username, password, name, lastName, pol, new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Kupac);

        if (kupacRepository.getByUsername(username) != null) {
            return null;
        } else {
            char ch;
            boolean brojFlag = false;
            boolean vSlovo = false;
            boolean ispravnaSifra = false;
            for (int i = 0; i < password.length(); i++) {
                ch = password.charAt(i);
                if (Character.isDigit(ch)) {
                    brojFlag = true;
                }
                if (Character.isUpperCase(ch)) {
                    vSlovo = true;
                }
            }
            if (vSlovo && brojFlag) {
                ispravnaSifra = true;
            }
            if (password.length() < 6 || !ispravnaSifra) {
                return null;
            } else {
                kupacRepository.save(kupac);
                return kupac;
            }
        }
    }

    public Kupac login(String username, String password) {
        List<Kupac> tmp = kupacRepository.findAll();
        for (Kupac k : tmp) {
            if (k.getUsername().equals(username)) {
                if (k.getPassword().equals(password)) {
                    return k;
                }
            }
        }
        return null;
    }


    public void napraviNarudzbinu(String restoran, Long IDS, Integer kolicine,Kupac k,Set<Porudzbina> listaPorudzbina){
        List<Restoran> listaRestorana = restoranRepository.findAll();
        Porudzbina porudzbina = new Porudzbina();
        Restoran trenutniRestoran = new Restoran();
        ArtikliUPorudzbini tmp = new ArtikliUPorudzbini();

        for(Porudzbina p : listaPorudzbina){
            if(p.getStatus().equals(Porudzbina.STATUS.Porucivanje)){ //Umesto obrada stavljen null.
                porudzbina = p;
            }
        }

        for(Restoran rest : listaRestorana){
            if(rest.getNaziv().equals(restoran)){ // Pronadjemo prosledjeno ime restorana u repou i uzmemo taj restoran u trenutni restoran
                trenutniRestoran = rest;
            }
        }
        List<Artikal> listaPostojecihArtikala = new ArrayList<>(trenutniRestoran.getMeni()); //Dobavimo meni iz trenutnogRestorana

        for(Artikal artikal : listaPostojecihArtikala){
            if(IDS.equals(artikal.getId())){
                tmp.setArtikal(artikal);
                tmp.setBroj(kolicine);
                porudzbina.getListaArtikalaUPorudzbini().add(tmp);
                System.out.println(tmp.getArtikal().getName());
            }
        }
        porudzbina.setKupac(k);
        porudzbina.setRestoranIKP(trenutniRestoran);
        porudzbina.setStatus(Porudzbina.STATUS.Porucivanje); //Umesto obrada stavljeno je null.
        listaPorudzbina.add(porudzbina);
        //porudzbinaRepository.save(porudzbina);
        kupacRepository.save(k);
    }
    public void naruci(Kupac kupac){
        for(Porudzbina p : kupac.getListaPorudzbina()){
            if(p.getStatus() == Porudzbina.STATUS.Porucivanje){
                p.setStatus(Porudzbina.STATUS.Obrada);
                p.setDatum(new java.util.Date(System.currentTimeMillis()));
                porudzbinaRepository.save(p);
            }
        }
    }
    public void izbaciIzKorpe(Kupac kupac, UUID uuid, Long id){
        Porudzbina TMP = new Porudzbina();
        for(Porudzbina p : kupac.getListaPorudzbina()){
            if(p.getUUID().equals(uuid) && p.getStatus().equals(Porudzbina.STATUS.Porucivanje)){
                for(ArtikliUPorudzbini a : p.getListaArtikalaUPorudzbini()){
                    if(a.getArtikal().getId().equals(id)){
                        p.getListaArtikalaUPorudzbini().remove(a);
                        TMP = p;
                    }
                }
            }
        }
        porudzbinaRepository.save(TMP);
        kupacRepository.save(kupac);
    }
}
