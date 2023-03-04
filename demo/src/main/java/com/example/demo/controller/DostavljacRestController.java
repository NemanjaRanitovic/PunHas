package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.StatusDto;
import com.example.demo.model.Dostavljac;
import com.example.demo.model.Porudzbina;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class DostavljacRestController {
    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    DostavljacService dostavljacService;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private SessionService sessionService;


    @PutMapping("/api/dostavljac/preuzmi/{uuid}")
    public ResponseEntity<String> preuzmiPorudzbinu(@PathVariable(name = "uuid") UUID uuid, HttpSession session){
        if(!sessionService.validateSession(session)){
            return null;
        }
        Dostavljac loggedDostavljac = dostavljacRepository.getByUsername(sessionService.getUsername(session));
        dostavljacService.preuzmi(uuid,loggedDostavljac);
        return ResponseEntity.ok("Porudzbina preuzeta");
    }
    @GetMapping("/api/dostavljac/porudzbine")
    public List<Porudzbina> izlistaj(HttpSession session){
        List<Porudzbina> tmp = new ArrayList<>();
        List<Porudzbina> sve = porudzbinaRepository.findAll();
        if(!sessionService.validateSession(session)){
            return null;
        }
        Dostavljac loggedDostavljac = dostavljacRepository.getByUsername(sessionService.getUsername(session));
        for(Porudzbina p : sve){
            if(p.getStatus().equals(Porudzbina.STATUS.CekaDostavu)){
                tmp.add(p);
            }
        }
        return tmp;
    }
}
