package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(nullable = false, length = 200)
    private String description_title;

    @Column(nullable = false, columnDefinition="TEXT")
    private String description_detail;

    // Relacion Mucho a muchos caracteristicas
    @ManyToMany(mappedBy = "products")
    private Set <Politica> regulations;

    // Relacion Mucho a muchos caracteristicas

    @ManyToMany(mappedBy = "products")
    private Set <Caracteristica> features;

    // Relacion Ciudades
    @OneToMany(mappedBy = "product")
    private Set <Imagen> images;


    // Relacion Ciudades
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private Ciudad location;

    // Relacion Categoria
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categoria category;

    //Release 3
    // Relacion Reservas
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Reserva> bookings;

    public void addPolitica(Politica politica) {
        this.regulations.add(politica);
        politica.getProducts().add(this);
    }

    public void removePolitica(long politicaId){
        Politica politica = this.regulations.stream().filter(t -> t.getId() == politicaId).findFirst().orElse(null);
        if (politica != null) {
            this.regulations.remove(politica);
            politica.getProducts().remove(this);
        }
    }

    public void addCaracteristica(Caracteristica caracteristica) {
        this.features.add(caracteristica);
        caracteristica.getProducts().add(this);
    }

    public void removeCaracteristica(Long caracteristicaId){
        Caracteristica caracteristica = this.features.stream().filter(t -> t.getId() == caracteristicaId).findFirst().orElse(null);
        if (caracteristica != null) {
            this.features.remove(caracteristica);
            caracteristica.getProducts().remove(this);
        }
    }

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
