package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    Categoria categoria;
    Producto producto;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();

    }

    @Test
    void settersTest() {
        producto = new Producto();

        categoria.setId(1L);
        categoria.setTitle("title.Test");
        categoria.setDescription("description.Test");
        categoria.setUrl("url.com.ar");
        categoria.setProducts(Set.of(producto));

        assertEquals(1L, categoria.getId());
        assertEquals("title.Test", categoria.getTitle());
        assertEquals("description.Test", categoria.getDescription());
        assertEquals("url.com.ar", categoria.getUrl());
        assertTrue(categoria.getProducts().contains(producto));
    }

    @Test
    void getIdTest() {
        Long idValue = 4L;
        categoria.setId(idValue);
        assertEquals(idValue, categoria.getId());
    }

    @Test
    void getTitleTest() {
        String titleValue = "title.Test";
        categoria.setTitle(titleValue);
        assertEquals(titleValue, categoria.getTitle());
    }

    @Test
    void getDescriptionTest() {
        String descriptionValue = "description.Test";
        categoria.setDescription(descriptionValue);
        assertEquals(descriptionValue, categoria.getDescription());
    }

    @Test
    void getUrlTest() {
        String urlValue = "url.com.ar";
        categoria.setUrl(urlValue);
        assertEquals(urlValue, categoria.getUrl());
    }

    @Test
    void getProductsTest() {
        categoria.setProducts(null);
        assertNull(categoria.getProducts());
    }
}