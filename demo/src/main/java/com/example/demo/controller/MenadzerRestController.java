package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.model.Artikal;
import com.example.demo.model.Menadzer;
import com.example.demo.model.Porudzbina;
import com.example.demo.model.Restoran;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.RestoranService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private RestoranService restoranService;
    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @PostMapping("/api/menadzer/dodavanjeArtikla")
    public ResponseEntity<String> dodajArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session){
       //Menadzer loggedMenadzer = (Menadzer) session.getAttribute("Menadzer");
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));

        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, artikal se ne moze dodati",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.addArticle(artikalDto.getNaziv(),artikalDto.getCena(),artikalDto.getTip(),loggedMenadzer);
        return ResponseEntity.ok("Artikal dodat");
    }

    @PutMapping("/api/menadzer/dodavanjeKomentara")
    public ResponseEntity<String> dodajKomentar(@RequestBody KomentarDto komentarDto, HttpSession session){
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce uneti komentar",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.dodajKomentar(komentarDto.getNazivProizvoda(),komentarDto.getKomentar(),loggedMenadzer);
        return ResponseEntity.ok("Komentar dodat");
    }

    //OVO MORA DA SE ZAVRSI KADA PROMENIMO VEZE DA BUDU KAKO TREBA
    @DeleteMapping("/api/menadzer/brisanjeArtikla")
    public ResponseEntity<String> obrisiArtikal(@RequestBody ImeDto imeDto,HttpSession session){
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce izbrisati artikal",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.obrisiArtikal(imeDto.getName(),loggedMenadzer);
        return ResponseEntity.ok("Artikal obrisan");

    }

    //stavi se default dugme da je vrednost 0 na sajtu kada ovaj oce da menja, ako ne zeli ostavi 0, i to proverimo ovde
    @PutMapping("/api/menadzer/izmeniArtikal")
    public ResponseEntity<String> izmeniArtikal(@RequestBody ArtikalIzmenaDto artikalIzmenaDto, HttpSession session){
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce menjanje artikala",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.izmeniArtikal(artikalIzmenaDto.getNaziv(),artikalIzmenaDto.getNoviNaziv(),artikalIzmenaDto.getCena(),artikalIzmenaDto.getTip(),loggedMenadzer);
        return ResponseEntity.ok("Artikal izmenjen");
    }
    @PutMapping("/api/menadzer/status")
    public ResponseEntity<String> otvaranje(HttpSession session){
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));

        if(loggedMenadzer == null){
            return new ResponseEntity<>("Nema ulogovanih menadzera, nije moguce menjanje artikala",HttpStatus.METHOD_NOT_ALLOWED);
        }
        menadzerService.otvori(loggedMenadzer);
        return ResponseEntity.ok("Restoran otvoren");
    }

    @DeleteMapping("/api/menadzer/izbrisiArtikal/{id}")
    public ResponseEntity<String> obrisi(@PathVariable(name = "id")Long Id, HttpSession session){
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));

        menadzerService.obrisiArtikal2(loggedMenadzer,Id);
        return ResponseEntity.ok("Artikal obrisan");
    }
    @GetMapping("/api/menadzer/pregledPorudzbina")
    public List<Porudzbina> pregledajPorudzbine(HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        Object kljuc = loggedMenadzer.getRestoran().getId();
        List<Porudzbina> tmp = porudzbinaRepository.findAll();
        List<Porudzbina> ispis = new ArrayList<>();
        for(Porudzbina p : tmp){
            if(p.getRestoranIKP().getId().equals(kljuc)){
                ispis.add(p);
            }
        }
        return ispis;
    }
    @GetMapping("/api/menadzer/pregledRestorana")
    public Restoran pregledRestorana(HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        return loggedMenadzer.getRestoran();
    }
    @GetMapping("/api/menadzer/pregledMojihRestorana")
    public List<Restoran> listaRestorana(HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        List<Restoran> listaRestorana = new ArrayList<>();
        for(Restoran r : restoranService.pronadjiSve()){
            if(loggedMenadzer.getRestoran().getId().equals(r.getId())){
                listaRestorana.add(r);
            }
        }
        return listaRestorana;
    }
    @GetMapping("/api/menadzer/pregledArtikala")
    public Set<Artikal> pregledArtikala(HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        return loggedMenadzer.getRestoran().getMeni();
    }
    @PutMapping("/api/menadzer/izmeniPorudzbinu/{uuid}")
    public ResponseEntity<String> izmeniPorudzbinu(@PathVariable(name = "uuid")UUID uuid,HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Menadzer loggedMenadzer = menadzerRepository.getByUsername(sessionService.getUsername(session));
        menadzerService.izmeniPorudzbinu(loggedMenadzer,uuid);

        return ResponseEntity.ok("Status promenjen");
    }



}
