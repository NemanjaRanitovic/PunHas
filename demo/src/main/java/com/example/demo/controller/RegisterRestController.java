package com.example.demo.controller;

import com.example.demo.dto.RegisterDto;
import com.example.demo.model.Admin;
import com.example.demo.model.Dostavljac;
import com.example.demo.model.Menadzer;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RegisterRestController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/api/register/menadzer")
    public ResponseEntity<String> createManager(@RequestBody RegisterDto registerDto, HttpSession session){
        if(registerDto.getUsername().isEmpty() ||
           registerDto.getPassword().isEmpty() ||
           registerDto.getName().isEmpty() ||
           registerDto.getLastName().isEmpty()
        ){
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);
        }
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Admin loggedAdmin = adminRepository.getByUsername(sessionService.getUsername(session));
        if(loggedAdmin == null){
            return new ResponseEntity<>("Nema ulogovanih admina, ne moze se kreirati menadzer", HttpStatus.METHOD_NOT_ALLOWED);
        }else {
            Menadzer noviMenadzer = adminService.createManager(registerDto.getUsername(), registerDto.getPassword(), registerDto.getName(), registerDto.getLastName(), registerDto.getPol());
        }
        return ResponseEntity.ok("Menadzer kreiran");
    }
    @PostMapping("/api/register/dostavljac")
    public ResponseEntity<String> createDostavljac(@RequestBody RegisterDto registerDto, HttpSession session){
        if(registerDto.getUsername().isEmpty() ||
                registerDto.getPassword().isEmpty() ||
                registerDto.getName().isEmpty() ||
                registerDto.getLastName().isEmpty()
        ){
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);
        }
        if(!sessionService.validateSession(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        Admin loggedAdmin = adminRepository.getByUsername(sessionService.getUsername(session));
        if(loggedAdmin == null){
            return new ResponseEntity<>("Nema ulogovanih admina, ne moze se kreirati dostavljac", HttpStatus.METHOD_NOT_ALLOWED);
        }else {
            Dostavljac noviDostavljac = adminService.createDostavljac(registerDto.getUsername(), registerDto.getPassword(), registerDto.getName(), registerDto.getLastName(), registerDto.getPol());
        }
        return ResponseEntity.ok("Dostavljac kreiran");
    }


}
