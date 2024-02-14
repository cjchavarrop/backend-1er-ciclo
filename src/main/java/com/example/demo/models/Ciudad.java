package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cities")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    @JsonIgnore
    private Set<Producto> products;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    @JsonIgnore
    private Set<Usuario> users;

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

    public Set<Producto> getProducts() {
        return products;
    }

    public void setProducts(Set<Producto> products) {
        this.products = products;
    }

    public Set<Usuario> getUsers() {
        return users;
    }

    public void setUsers(Set<Usuario> users) {
        this.users = users;
    }
}
