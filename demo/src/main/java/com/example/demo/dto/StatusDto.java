package com.example.demo.dto;

import com.example.demo.model.Porudzbina;

public class StatusDto {
    private Porudzbina.STATUS status;

    public StatusDto(Porudzbina.STATUS status) {
        this.status = status;
    }
    public StatusDto(){}

    public Porudzbina.STATUS getStatus() {
        return status;
    }

    public void setStatus(Porudzbina.STATUS status) {
        this.status = status;
    }
}
