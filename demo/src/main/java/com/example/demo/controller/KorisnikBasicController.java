package com.example.demo.controller;


import com.example.demo.model.Korisnik;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class KorisnikBasicController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/")
    public String welecome(){
        return "index.html";
    }

    @GetMapping("/korisnici")
    public String dajKorisnike(Model model){
        List<Korisnik> listaKorisnika = korisnikService.pronadjiSve();
        model.addAttribute("korisnici", listaKorisnika);
        return "korisnici.html";
    }

    @GetMapping("/delete/{id}")
    public String vratiSe(@PathVariable Long id){
        korisnikService.obrisiPoId(id);
        return "redirect:/";
    }

}