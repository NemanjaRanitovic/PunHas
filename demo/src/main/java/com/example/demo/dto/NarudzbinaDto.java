package com.example.demo.dto;

import java.util.List;

public class NarudzbinaDto {

    private String restoran;
    private Long Id;
    private Integer kolicina;

    public NarudzbinaDto(){}

    public NarudzbinaDto(String restoran, Long id, Integer kolicina) { //U POSTMANU MALO id GLEDA SE PROSLEDJIVANJE ZA KONSTRUKTOR XD
        this.restoran = restoran;
        this.Id = id;
        this.kolicina = kolicina;
    }

    public String getRestoran() {
        return restoran;
    }

    public void setRestoran(String restoran) {
        this.restoran = restoran;
    }

    public  Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
