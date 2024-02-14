package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void constructorTest() {
        Producto producto = new Producto();
        assertNotNull(producto);
    }

    @Test
    void settersTest() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setTitle("title");
        producto.setDescription_title("description_title");
        producto.setDescription_detail("description_detail");
        producto.setLocation(new Ciudad());
        producto.setCategory(new Categoria());

        assertNotNull(producto);
    }

    @Test
    void gettersTest() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setTitle("title");
        producto.setDescription_title("description_title");
        producto.setDescription_detail("description_detail");
        producto.setLocation(new Ciudad());
        producto.getLocation().setId(1L);
        producto.getLocation().setCity("Bariloche test");
        producto.setCategory(new Categoria());
        producto.getCategory().setId(1L);
        producto.getCategory().setDescription("description test");


        assertEquals(1L, producto.getId());
        assertEquals("title", producto.getTitle());
        assertEquals("description_title", producto.getDescription_title());
        assertEquals("description_detail", producto.getDescription_detail());
        assertNotNull(producto.getLocation().getId());
        assertNotNull(producto.getCategory().getId());
    }

}