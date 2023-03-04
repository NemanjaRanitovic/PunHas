package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public Admin login(String username,String password){
        Admin admin = adminRepository.getByUsername(username);
        if(admin == null || !admin.getPassword().equals(password)){
            return null;
        }
        return admin;
    }

    public Menadzer createManager(String username, String password, String name, String lastName, Korisnik.POLOVI pol){
        Menadzer kreiraniMenadzer = new Menadzer(username,password,name,lastName,pol);

        menadzerRepository.save(kreiraniMenadzer);
        korisnikRepository.save(kreiraniMenadzer);

        return kreiraniMenadzer;
    }

    public Dostavljac createDostavljac(String username, String password, String name, String lastName, Korisnik.POLOVI pol){
        Dostavljac kreiranDostavljac = new Dostavljac(username,password,name,lastName,pol);

        dostavljacRepository.save(kreiranDostavljac);
        korisnikRepository.save(kreiranDostavljac);

        return kreiranDostavljac;
    }

    public Restoran createRestoran(String naziv,String adresa,String tip,double gSirina,double gVisina,String menadzer,boolean radi){
        Restoran kreiraniRestoran = new Restoran(naziv,adresa,tip,gSirina,gVisina,true);
        Menadzer tmp = menadzerRepository.getByUsername(menadzer);
        tmp.setRestoran(kreiraniRestoran);
        lokacijaRepository.save(kreiraniRestoran.getLokacija());
        restoranRepository.save(kreiraniRestoran);
        return kreiraniRestoran;
    }

    // pravljenje korisnika



}
