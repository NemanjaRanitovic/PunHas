package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.KupacRepository;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.KupacService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class KupacRestController {
    @Autowired
    private KupacService kupacService;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private KupacRepository kupacRepository;

    @PostMapping("/api/kupac/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto, HttpSession session){
        if(registerDto.getUsername().isEmpty() ||
                registerDto.getPassword().isEmpty() ||
                registerDto.getName().isEmpty()     ||
                registerDto.getLastName().isEmpty() ||
                !registerDto.getPol().getDeclaringClass().isEnum())
        {

            return new ResponseEntity<>("Invalid login data", HttpStatus.BAD_REQUEST);
        }
        Kupac registrovanKupac = kupacService.register(registerDto.getUsername(),registerDto.getPassword(),registerDto.getName(),registerDto.getLastName(),registerDto.getPol());
        if(registrovanKupac == null){
            return new ResponseEntity<>("Korisnik vec postoji, ili sifra ne ispunjava kriterijum!", HttpStatus.BAD_REQUEST);
        }
        session.setAttribute("Kupac", registrovanKupac);
        return ResponseEntity.ok("Uspesna registracija");
    }

    //Ideja je da se prvo kreiraju funckionalnosti za pravljenje porudzbine.
    //Pregledi porudzbina : Tacke 1-3 u delu porudzbine mogu brzo da se implementiraju
    // tako da krecem od tacke 4

    //u servis prosledjujem ime artikla i poredim sa listom artikala iz restorana iz kog je poruceno, ako ima, moze, ako nema ne moze

    //PorudzbinaDto
    /*
        --Ime artikla koji kupac porucuje
        --KORPA JE U SUSTINI ARTIKLI U PORUDZBINI
        --Izlistavaju se naziv kolicina cena(pojedinacna), i ukupna cena

     */
    @PostMapping("/api/kupac/dodajukorpu")
    public ResponseEntity<String> narudzbina(@RequestBody NarudzbinaDto narudzbinaDto, HttpSession session){

        if(narudzbinaDto.getId() == null || narudzbinaDto.getKolicina() == null){
            return new ResponseEntity("Invalid data", HttpStatus.BAD_REQUEST);
        }
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Kupac loggedKupac = kupacRepository.getByUsername(sessionService.getUsername(session));

        if(loggedKupac == null){
            return new ResponseEntity<>("Nema ulogovanih kupaca nije moguce poruciti", HttpStatus.BAD_REQUEST);
        }
        List<Restoran> listar = restoranRepository.findAll();
        for(Restoran r : listar){
            if(narudzbinaDto.getRestoran().equals(r.getNaziv())){
                if(r.isRadi()){
                    kupacService.napraviNarudzbinu(narudzbinaDto.getRestoran(),narudzbinaDto.getId(),narudzbinaDto.getKolicina(),loggedKupac,loggedKupac.getListaPorudzbina());
                    return ResponseEntity.ok("Dodato u korpu.");
                }
            }
        }
        return new ResponseEntity<>("Restoran ne radi!",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/api/kupac/izbaciizkorpe/{uuid}/{id}")
    public ResponseEntity<String> izbaci(@PathVariable(name = "uuid")UUID uuid,@PathVariable(name = "id")Long id,/* @RequestBody IdDto idDto, */HttpSession session){ //dodat long na putanju
        if(!sessionService.validateSession(session)){                                                             // @RequestBody IdDto idDto,
            return null;
        }
        Kupac loggedKupac = kupacRepository.getByUsername(sessionService.getUsername(session));

        kupacService.izbaciIzKorpe(loggedKupac, uuid, id);
        return ResponseEntity.ok("Izbacen iz korpe artikal sa id: " + id);
    }

    @PutMapping("/api/kupac/naruci")
    public ResponseEntity<String> naruci(HttpSession session){
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Kupac loggedKupac = kupacRepository.getByUsername(sessionService.getUsername(session));
        if(loggedKupac == null){
            return new ResponseEntity<>("Nema ulogovanih kupaca nije moguce poruciti", HttpStatus.BAD_REQUEST);
        }
        kupacService.naruci(loggedKupac);
        return ResponseEntity.ok("Naruceno");
    }
    @GetMapping("/api/kupac/porudzbine")
    public List<Porudzbina> izlistavanjePorudzbina(HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Kupac loggedKupac = kupacRepository.getByUsername(sessionService.getUsername(session));
        if(loggedKupac == null){
            return null;
        }
        List<Porudzbina> tmp = new ArrayList<>(loggedKupac.getListaPorudzbina());
        return tmp;
    }
    @GetMapping("/api/kupac/prikazArtikala/{id}")
    public Set<Artikal> pregledArtikala(@PathVariable(name = "id")Long id, HttpSession session){
        Restoran temp = new Restoran();

        if(!sessionService.validateSession(session)){
            return null;
        }
        Kupac loggedKupac = kupacRepository.getByUsername(sessionService.getUsername(session));

        List<Restoran> tmp = restoranRepository.findAll();
        for(Restoran r : tmp){
            if(r.getId().equals(id)){
                temp = r;
            }
        }
        return temp.getMeni();
    }

}
