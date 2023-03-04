package com.example.demo.dto;

public class ImeDto {
    private String name;

    public ImeDto(String name) {
        this.name = name;
    }
    public ImeDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
