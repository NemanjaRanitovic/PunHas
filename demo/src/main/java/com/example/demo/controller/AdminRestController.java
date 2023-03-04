package com.example.demo.controller;


import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.model.Admin;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Restoran;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.UsesSunMisc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/api/admin")
    public String pocetak(){return "Ovo je Admin rest Controller";}


    // LOGGED USERS
     @GetMapping("/api/admin/pregled")
    public ResponseEntity<List<KorisnikDto>> findAll(HttpSession session){
        List<Korisnik> listaKorisnika = korisnikService.pregledKorisnika();
         if(!sessionService.validateSession(session)){
             return new ResponseEntity(HttpStatus.FORBIDDEN);
         }

        Admin loggedAdmin = adminRepository.getByUsername(sessionService.getUsername(session));
        if(loggedAdmin == null){
            System.out.println("Nema sesije");
        }else{
            System.out.println(loggedAdmin);
        }
        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik korisnik : listaKorisnika){
            KorisnikDto dto = new KorisnikDto(korisnik);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
     }

     // CREATE RESTORAN
     @PostMapping("/api/admin/createRestoran")
    public ResponseEntity<String> createRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
         if (restoranDto.getAdresa().isEmpty() ||
                 restoranDto.getNaziv().isEmpty() ||
                 restoranDto.getTip().isEmpty()) {
             return new ResponseEntity("Invalid restoran data", HttpStatus.BAD_REQUEST);
         }
         if (menadzerService.findByUsername(restoranDto.getMenadzer()) == null) {
             return new ResponseEntity("Menadzer ne postoji", HttpStatus.BAD_REQUEST);
         }

         if(!sessionService.validateSession(session)){
             return new ResponseEntity(HttpStatus.FORBIDDEN);
         }

         Admin loggedAdmin = adminRepository.getByUsername(sessionService.getUsername(session));
         if(loggedAdmin == null){
             return new ResponseEntity<>("Nema ulogovanih admina, ne moze se kreirati restoran", HttpStatus.METHOD_NOT_ALLOWED);
         }else{
             Restoran noviRestoran = adminService.createRestoran(restoranDto.getNaziv(),restoranDto.getAdresa(),restoranDto.getTip(),restoranDto.getgSirina(),restoranDto.getgVisina(),restoranDto.getMenadzer(),restoranDto.isStatus());
         }
         return ResponseEntity.ok("Restoran kreiran");

     }

}
