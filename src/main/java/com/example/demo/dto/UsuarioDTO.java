package com.example.demo.dto;

import com.example.demo.models.Ciudad;
import com.example.demo.models.Rol;

public class UsuarioDTO {

    private Long id;
    private String given_name;
    private String sur_name;
    private String email;
    private Rol rol;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Ciudad getLocation() {
        return location;
    }

    public void setLocation(Ciudad location) {
        this.location = location;
    }
}
