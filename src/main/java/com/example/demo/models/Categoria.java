package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(nullable = false, length = 300)
    private String description;

    @Column(nullable = false, length = 400)
    private String url;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @JsonIgnore
    private Set<Producto> products;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Producto> getProducts() {
        return products;
    }

    public void setProducts(Set<Producto> products) {
        this.products = products;
    }
}
