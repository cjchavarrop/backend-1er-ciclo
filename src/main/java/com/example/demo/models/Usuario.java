package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String given_name;

    @Column(nullable = false, length = 200)
    private String sur_name;

    @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    // Relacion Roles
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    // Relacion Reservas
    @OneToMany(mappedBy = "product")
    private Set<Reserva> bookings;

    // Relacion City
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private Ciudad location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSur_name() {
        return sur_name;
    }

    public void setSur_name(String sur_name) {
        this.sur_name = sur_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Set<Reserva> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Reserva> bookings) {
        this.bookings = bookings;
    }

    public Ciudad getLocation() {
        return location;
    }

    public void setLocation(Ciudad location) {
        this.location = location;
    }
}
