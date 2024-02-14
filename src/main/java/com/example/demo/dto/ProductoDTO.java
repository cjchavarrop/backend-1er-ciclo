package com.example.demo.dto;

import com.example.demo.models.*;

import java.util.Set;

public class ProductoDTO {
    private Long id;
    private String title;
    private String description_title;
    private String description_detail;
    private Set <Politica> regulations;
    private Set <Caracteristica> features;
    private Set <Imagen> images;
    private Ciudad location;
    private Categoria category;
    private Set<Reserva> bookings;

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

    public Set<Politica> getRegulations() {
        return regulations;
    }

    public void setRegulations(Set<Politica> regulations) {
        this.regulations = regulations;
    }

    public Set<Caracteristica> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Caracteristica> features) {
        this.features = features;
    }

    public Set<Imagen> getImages() {
        return images;
    }

    public void setImages(Set<Imagen> images) {
        this.images = images;
    }

    public Ciudad getLocation() {
        return location;
    }

    public void setLocation(Ciudad location) {
        this.location = location;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }


    public Set<Reserva> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Reserva> bookings) {
        this.bookings = bookings;
    }
}
