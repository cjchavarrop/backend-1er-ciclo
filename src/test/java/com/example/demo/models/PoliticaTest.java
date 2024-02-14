package com.example.demo.models;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PoliticaTest {

    @Test
    void constructorTest() {
        Politica politica = new Politica();
        assertNotNull(politica);
    }

    @Test
    void getId() {
        Politica politica = new Politica();
        politica.setId(1L);
        assertEquals(1L, politica.getId());
    }

    @Test
    void getType() {
        Politica politica = new Politica();
        politica.setType("type");
        assertEquals("type", politica.getType());
    }

    @Test
    void getTitle() {
        Politica politica = new Politica();
        politica.setTitle("title");
        assertEquals("title", politica.getTitle());
    }

    @Test
    void getDescription() {
        Politica politica = new Politica();
        politica.setDescription("description");
        assertEquals("description", politica.getDescription());
    }

    @Test

    void getProducts() {
        Politica politica = new Politica();
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setCategory(new Categoria());
        producto.setTitle("titleTest");
        producto.setLocation(new Ciudad());
        producto.setImages(Set.of(new Imagen()));
        producto.setRegulations(null);
        politica.setProducts(Set.of(producto));

        assertEquals(Set.of(producto), politica.getProducts());
    }

}