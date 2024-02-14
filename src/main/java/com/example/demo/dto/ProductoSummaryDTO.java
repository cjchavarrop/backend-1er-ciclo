package com.example.demo.dto;

import com.example.demo.models.Categoria;
import com.example.demo.models.Ciudad;
import com.example.demo.models.Imagen;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Set;

public class ProductoSummaryDTO {
    private Long id;
    private String title;
    private Categoria category;
    private Ciudad location;

    private Set <Imagen> images;

    private String description_title;
    private String description_detail;

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

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public Ciudad getLocation() {
        return location;
    }

    public void setLocation(Ciudad location) {
        this.location = location;
    }

    public Imagen getImages() {
        Set<Imagen> imagenes = this.images;
        Imagen first_image = imagenes.stream().findFirst().orElse(null);
        return first_image;
    }

    public String getDescription_title() {
        return description_title;
    }

    public void setDescription_title(String description_title) {
        this.description_title = description_title;
    }

    public String getDescription_detail() {
        return description_detail;
    }

    public void setDescription_detail(String description_detail) {
        this.description_detail = description_detail;
    }
}
