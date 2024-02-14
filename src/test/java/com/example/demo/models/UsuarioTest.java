package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test

    void testGivenName() {
        Usuario usuario = new Usuario();
        usuario.setGiven_name("Juan");
        assertEquals("Juan", usuario.getGiven_name());
    }

    @Test

    void testSurName() {
        Usuario usuario = new Usuario();
        usuario.setSur_name("Perez");
        assertEquals("Perez", usuario.getSur_name());
    }

    @Test

    void testEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("juanperez@gmail.com");
        assertEquals("juanperez@gmail.com", usuario.getEmail());
    }

}