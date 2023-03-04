package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private KupacRepository kupacRepository;

    public Menadzer findByUsername(String username) {
        Optional<Menadzer> pronadjeni = Optional.ofNullable(menadzerRepository.getByUsername(username));
        return pronadjeni.orElse(null);
    }

    public Menadzer login(String username, String password) {
        Menadzer menadzer = menadzerRepository.getByUsername(username);
        if (menadzer == null || !menadzer.getPassword().equals(password)) {
            return null;
        }
        return menadzer;
    }

    public void addArticle(String naziv, double cena, Artikal.Tip tip, Menadzer menadzer) {
        Artikal artikal = new Artikal(tip, naziv, cena);
        artikal.setRestoran(menadzer.getRestoran());
        artikalRepository.save(artikal);
        menadzer.getRestoran().getMeni().add(artikal);
        restoranRepository.save(menadzer.getRestoran());
    }

    public void dodajKomentar(String naziv, String komentar,Menadzer menadzer) {
        Set<Artikal> artikli =  menadzer.getRestoran().getMeni();
        for (Artikal artikal : artikli) {
            if (artikal.getName().equals(naziv)) {
                artikal.setOpis(komentar);
                artikalRepository.save(artikal);
            }
        }
    }

    public void obrisiArtikal(String ime,Menadzer menadzer){
        Set<Artikal> artikli =  menadzer.getRestoran().getMeni();
        for(Artikal artikal : artikli){
            if(artikal.getName().equals(ime)){
                menadzer.getRestoran().getMeni().remove(artikal);
                artikalRepository.delete(artikal);
            }
        }
    }
    public void obrisiArtikal2(Menadzer menadzer, Long id){
        Set<Artikal> artikli = menadzer.getRestoran().getMeni();
        for(Artikal artikal : artikli){
            if(artikal.getId() == id){
                menadzer.getRestoran().getMeni().remove(artikal);
                artikalRepository.delete(artikal);
            }
        }
        menadzerRepository.save(menadzer);
        restoranRepository.save(menadzer.getRestoran());

    }

    public void izmeniArtikal(String naziv,String novinaziv, double cena, Artikal.Tip tip,Menadzer menadzer){
        Set<Artikal> artikli =  menadzer.getRestoran().getMeni(); //pretraga ce da se radi tako sto ce menadzer morati da unese ime artikla da bi ga izmenio
        for(Artikal artikal : artikli){
            if(artikal.getName().equals(naziv)){
                if(!novinaziv.isEmpty()){
                    artikal.setName(novinaziv);
                    artikalRepository.save(artikal);
                }
                if(cena != 0){
                    artikal.setCena(cena);
                    artikalRepository.save(artikal);
                }
                if(artikal.getTip().getDeclaringClass().isEnum()){
                    artikal.setTip(tip);
                    artikalRepository.save(artikal);
                }
            }
        }
    }
    public void otvori(Menadzer menadzer){
        menadzer.getRestoran().setRadi(true);
        menadzerRepository.save(menadzer);
        restoranRepository.save(menadzer.getRestoran());
    }
    public void izmeniPorudzbinu(Menadzer menadzer, UUID uuid){
        Object kljuc = menadzer.getRestoran().getId();
        Porudzbina temp = null;
        Kupac k = null;
        List<Porudzbina> tmp = porudzbinaRepository.findAll();
        List<Porudzbina> obrada = new ArrayList<>();
        for(Porudzbina p : tmp){
            if(p.getRestoranIKP().getId().equals(kljuc)){
                obrada.add(p);
            }
        }
        for(Porudzbina p : obrada){
            if(p.getUUID().equals(uuid)){
                temp = p;
                temp.setStatus(Porudzbina.STATUS.Priprema);
                temp.setStatus(Porudzbina.STATUS.CekaDostavu);
            }
        }
        k = temp.getKupac();
        porudzbinaRepository.save(temp);
        kupacRepository.save(k);
        menadzerRepository.save(menadzer);
    }
}
