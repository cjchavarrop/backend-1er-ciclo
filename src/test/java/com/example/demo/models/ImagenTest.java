package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImagenTest {

    @Test
    void constructorTest() {
        Imagen imagen = new Imagen();
        assertNotNull(imagen);
    }

    @Test
    void settersTest() {
        Imagen imagen = new Imagen();
        imagen.setId(1L);
        imagen.setTitle("title");
        imagen.setUrl("url.com.ar");
        imagen.setProduct(new Producto());

        assertNotNull(imagen);
    }

    @Test
    void gettersTest() {
        Imagen imagen = new Imagen();
        imagen.setId(1L);
        imagen.setTitle("title");
        imagen.setUrl("url.com.ar");

        assertEquals(1L, imagen.getId());
        assertNotNull(imagen.getTitle());
        assertNotNull(imagen.getUrl());
    }


}