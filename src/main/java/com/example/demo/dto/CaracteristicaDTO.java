package com.example.demo.dto;

import com.example.demo.models.Producto;

import java.util.Set;

public class CaracteristicaDTO {

    private Long id;
    private String title;
    private String icon;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
