package com.example.demo.dto;

public class TipDto {
    private String tip;

    public TipDto(String tip) {
        this.tip = tip;
    }
    public TipDto(){}

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
