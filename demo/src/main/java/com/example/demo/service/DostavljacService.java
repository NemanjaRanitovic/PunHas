package com.example.demo.service;

import com.example.demo.model.Dostavljac;
import com.example.demo.model.Kupac;
import com.example.demo.model.Menadzer;
import com.example.demo.model.Porudzbina;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DostavljacService {

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public Dostavljac login(String username, String password) {
        Dostavljac dostavljac= dostavljacRepository.getByUsername(username);
        if (dostavljac == null || !dostavljac.getPassword().equals(password)) {
            return null;
        }
        return dostavljac;
    }
    public void preuzmi(UUID uuid, Dostavljac dostavljac){
        List<Porudzbina> tmp = porudzbinaRepository.findAll();
        Porudzbina porudzbina = null;
        Kupac kupac = null;
        for(Porudzbina p : tmp){
            if(p.getUUID().equals(uuid) && p.getStatus().equals(Porudzbina.STATUS.CekaDostavu)){ //dodat dodatan uslov za dostavljaca
                porudzbina = p;
                porudzbina.setStatus(Porudzbina.STATUS.uTransportu);
                porudzbina.setStatus(Porudzbina.STATUS.Dostavljena);
            }
        }
        double bodovi = porudzbina.getKupac().getBrojSkupljenihBod();
        porudzbina.getKupac().setBrojSkupljenihBod((bodovi+=porudzbina.getUCena()/1000*133));
        kupac = porudzbina.getKupac();
        porudzbinaRepository.save(porudzbina);
        dostavljacRepository.save(dostavljac);
    }

}
