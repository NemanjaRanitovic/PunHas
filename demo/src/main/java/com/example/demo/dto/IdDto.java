package com.example.demo.dto;

public class IdDto {

    private Long id;

    public IdDto(Long id){
        this.id = id;
    }
    public IdDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
