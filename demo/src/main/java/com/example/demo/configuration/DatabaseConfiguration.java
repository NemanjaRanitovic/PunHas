package com.example.demo.configuration;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;


@Configuration
public class DatabaseConfiguration {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ArtikalRepository artikalRepository;
    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Bean
    public boolean instantiate(){
        // ADMINI
        Admin admin1 = new Admin("aleksasimeunovic","aleksa123","Aleksa","Simeunovic", Korisnik.POLOVI.Musko,new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Admin);
        adminRepository.save(admin1);

        // KORISNICI
        Korisnik korisnik1 = new Korisnik("nemanjaranitovic","nemanja123","Nemanja","Ranitovic", Korisnik.POLOVI.Musko,new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Admin);
        Korisnik korisnik2 = new Korisnik("nikolaradovic", "nikola123","Nikola","Radovic", Korisnik.POLOVI.Musko,new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Dostavljac);
        Korisnik korisnik3 = new Korisnik("nemanjatodorovic","nemanja123","Nemanja","Todorovic", Korisnik.POLOVI.Musko,new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Menadzer);
        Korisnik korisnik4 = new Korisnik("nikolacvejic","nikola123","Nikola","Cvejic", Korisnik.POLOVI.Musko,new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Dostavljac);
        Korisnik korisnik5 = new Korisnik("vladimirblanusa","vladimir123","Vladimir","Blanusa", Korisnik.POLOVI.Musko,new java.util.Date(System.currentTimeMillis()), Korisnik.ULOGA.Kupac);
        korisnikRepository.saveAll(
                List.of(korisnik1, korisnik2, korisnik3, korisnik4, korisnik5)
        );

        // RESTORANI
        Restoran restoran = new Restoran("canzona", "Janka Cmelika 23","Italijanski",200,300,true);
        restoranRepository.save(restoran);

        // ARTIKLI
       // Artikal artikal1 = new Artikal(Artikal.Tip.Jelo,"Sis Cevap",267,1);
       // artikal1.setOpis("Mnogo dobar sis cevap");
       // artikalRepository.save(artikal1);

      //  Artikal artikal2 = new Artikal(Artikal.Tip.Jelo,"Pizza",180,1);
      //  artikal1.setOpis("Cappricosa");
      //  artikalRepository.save(artikal2);



        // PORUDZBINE
        //Porudzbina porudzbina1 = new Porudzbina ("Canzona", new java.util.Date(System.currentTimeMillis()), korisnik5, Porudzbina.STATUS.Priprema);

        return true;
    }
}