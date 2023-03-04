package com.example.demo.dto;

import java.util.List;
import java.util.Set;

public class PorudzbinaDto {

    private String restoran;

    private Set<String> artikli;

    private List<Integer> kolicina;

    public PorudzbinaDto(){}

    public PorudzbinaDto(String restoran, Set<String> artikli, List<Integer> kolicina) {
        this.restoran = restoran;
        this.artikli = artikli;
        this.kolicina = kolicina;
    }

    public String getRestoran() {
        return restoran;
    }

    public void setRestoran(String restoran) {
        this.restoran = restoran;
    }

    public Set<String> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<String> artikli) {
        this.artikli = artikli;
    }

    public List<Integer> getKolicina() {
        return kolicina;
    }

    public void setKolicina(List<Integer> kolicina) {
        this.kolicina = kolicina;
    }
}
