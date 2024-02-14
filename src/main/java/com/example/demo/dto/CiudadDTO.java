package com.example.demo.dto;

import com.example.demo.models.Producto;

import java.util.Set;

public class CiudadDTO {
    private Long id;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
