package com.example.demo.dto;

public class KomentarDto {
    private String komentar;
    private String nazivProizvoda;

    public KomentarDto(String komentar,String nazivProizvoda) {
        this.komentar = komentar;
        this.nazivProizvoda = nazivProizvoda;
    }
    public KomentarDto(){}

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }
}
