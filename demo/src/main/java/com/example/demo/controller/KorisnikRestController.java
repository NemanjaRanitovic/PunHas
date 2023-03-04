package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.model.Artikal;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Restoran;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RestoranService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private SessionService sessionService;


    @GetMapping("/api/")
    public String welecome() {
        return "Pozdrav iz API-ja!";
    }

    @GetMapping("/api/korisnici")
    public List<Korisnik> listaKorisnika() {
        List<Korisnik> listaK = korisnikService.pronadjiSve();
        return listaK;
    }

    @DeleteMapping("/api/obrisi-korisnika/{id}")
    public List<Korisnik> listanakonBrisanja(@PathVariable(name = "id") Long id) {
        this.korisnikService.obrisiPoId(id);
        List<Korisnik> tmp = korisnikService.pronadjiSve();
        return tmp;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if (loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty()) {
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);
        }

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getUsername(), loginDto.getPassword());

        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);
        }


        session.setAttribute("role", loggedKorisnik.getUloga());
        session.setAttribute("username",loggedKorisnik.getUsername());


        return new ResponseEntity(loggedKorisnik,HttpStatus.OK);

    }

    @GetMapping("/api/provera")
    public String provera(HttpSession session){
        if(session.getAttribute("Korisnik") != null){
            return "Korisnik je prijavljen!";
        }
        return null;
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto, HttpSession session){
        if(registerDto.getUsername().isEmpty() ||
           registerDto.getPassword().isEmpty() ||
           registerDto.getName().isEmpty()     ||
           registerDto.getLastName().isEmpty() ||
           !registerDto.getPol().getDeclaringClass().isEnum())
        {

            return new ResponseEntity<>("Invalid login data", HttpStatus.BAD_REQUEST);
        }
        Korisnik registrovanKorisnik = korisnikService.register(registerDto.getUsername(),registerDto.getPassword(),registerDto.getName(),registerDto.getLastName(),registerDto.getPol());
        if(registrovanKorisnik == null){
            return new ResponseEntity<>("Korisnik vec postoji, ili sifra ne ispunjava kriterijum!", HttpStatus.BAD_REQUEST);
        }
        session.setAttribute("Korisnik", registrovanKorisnik);
        return ResponseEntity.ok("Uspesna registracija");
    }

    @GetMapping("/api/korisnik/pregled") //Treba izmeniti sa sessionservice
    public Korisnik pregled(HttpSession session, HttpServletRequest request){
        if(session.getAttribute("Korisnik") != null){
            Korisnik loggedKorisnik = (Korisnik) session.getAttribute("Korisnik");
            List<Korisnik> tmp = korisnikService.pronadjiSve();
            for(Korisnik k : tmp){
                if(k.getUsername().equals(loggedKorisnik.getUsername())){
                    return k;
                }
            }
    }
        return null;
    }

    @PutMapping("/api/korisnik/izmeni") // IZMENA KORISNIKA Treba izmeniti sa session service
    public ResponseEntity<String> izmena(@RequestBody RegisterDto registerDto, HttpSession session){
        if(session.getAttribute("Korisnik") != null){
            Korisnik loggedKorisnik = (Korisnik) session.getAttribute("Korisnik");
            List<Korisnik> tmp = (List<Korisnik>) korisnikService.pronadjiSve();
            for(Korisnik k : tmp) {
                if (korisnikService.izmenaPodataka(loggedKorisnik.getUsername(), registerDto.getUsername(), registerDto.getPassword(), registerDto.getName(), registerDto.getLastName(), registerDto.getPol()) == false) {
                    return new ResponseEntity<>("Novi username je zauzet", HttpStatus.BAD_REQUEST);
                } else {
                    return ResponseEntity.ok("Podaci izmenjeni");
                }
            }
        }
        return new ResponseEntity<>("Nema ulogovanih korisnika", HttpStatus.BAD_REQUEST);
    }

    // RESTORAN

    @GetMapping("/api/pregledRestorana")
    public List<Restoran> listaRestorana () {
        List<Restoran> listaRestorana = restoranService.pronadjiSve();
        return listaRestorana;
    }

    @GetMapping("/api/pretragaRestoranaPoImenu")
    public List<Restoran> pretragaRestoranaPoImenu(@RequestBody ImeDto imeDto){
        List<Restoran> listaRestorana = restoranService.pronadjiSve();
        List<Restoran> prikazniRestorani = new ArrayList<>();
        for(Restoran r : listaRestorana){
            if(r.getNaziv().contains(imeDto.getName())){
                prikazniRestorani.add(r);
            }
        }

    return prikazniRestorani;
    }


    @GetMapping("/api/pretragaRestoranaPoTipu")
    public List<Restoran> pretragaRestoranaPoTipu(@RequestBody TipDto tipDto){
        List<Restoran> listaRestorana = restoranService.pronadjiSve();
        List<Restoran> prikazniRestorani = new ArrayList<>();
        for(Restoran r : listaRestorana){
            if(r.getTip().contains(tipDto.getTip())){
                prikazniRestorani.add(r);
            }
        }

        return prikazniRestorani;
    }

    @GetMapping("/api/pretragaRestoranaPoLokaciji")
    public List<Restoran> pretragaRestoranaPoLokaciji(@RequestBody LokacijaDto lokacijaDto){
        List<Restoran> listaRestorana = restoranService.pronadjiSve();
        List<Restoran> prikazniRestorani = new ArrayList<>();
        if(lokacijaDto.getAdresa().isEmpty()||
           lokacijaDto.getgSirina() == 0 ||
           lokacijaDto.getgVisina() == 0){

            return null; // Ovde nisam siguran kako da izvrsim proveru da li su dobro uneti parametri.
            //za sada mora ovako: ako necu da pretrazujem po visini i sirini nego samo po adresi, stavim parametar koji znam da sigurno nece biti za visinu i sirinu, pa ce naci samo po imenu
        }

        for(Restoran r : listaRestorana){
            if(r.getLokacija().getgSirina() == lokacijaDto.getgSirina()){
                prikazniRestorani.add(r);
            }
        }
        for(Restoran r : listaRestorana){
            if(r.getLokacija().getAdresa().contains(lokacijaDto.getAdresa())){
                prikazniRestorani.add(r);
            }
        }
        for(Restoran r : listaRestorana){
            if(r.getLokacija().getgVisina() == lokacijaDto.getgVisina()){
                prikazniRestorani.add(r);
            }
        }

        return prikazniRestorani;
    }
    @GetMapping("/api/pretragaRestorana")
    public List<Restoran> pretragaRestorana(@RequestBody PretragaDto pretragaDto){
        List<Restoran> listaRestorana = restoranService.pronadjiSve();
        List<Restoran> prikazaniRestorani = new ArrayList<>();

        for(Restoran r : listaRestorana){
            if(r.getLokacija().getAdresa().contains(pretragaDto.getParametri())){
                prikazaniRestorani.add(r);
            }
        }

        for(Restoran r : listaRestorana){
            if(r.getNaziv().contains(pretragaDto.getParametri())){  // Ovaj je za naziv
                prikazaniRestorani.add(r);
            }
        }

        for(Restoran r : listaRestorana){
            if(r.getTip().contains(pretragaDto.getParametri())){
                prikazaniRestorani.add(r);
            }
        }
        return prikazaniRestorani;
    }

    @GetMapping("/api/prikazRestorana/{id}") ///api/prikazRestorana/{id}
    public Optional<Restoran> prikazRestorana(@PathVariable(name = "id") Long id){
        Optional<Restoran> restoranZaPrikaz = restoranService.nadjiPoId(id);
        return restoranZaPrikaz;
    }

}