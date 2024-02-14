package com.example.demo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CiudadTest {

    @Test
    void constructorTest() {
        Ciudad ciudadTest = new Ciudad();
        assertNotNull(ciudadTest);
    }

    @Test
    void settersTest() {
        Ciudad ciudadTest = new Ciudad();
        ciudadTest.setId(1L);
        ciudadTest.setCity("city.Test");
        ciudadTest.setProducts(null);

        assertEquals(1L, ciudadTest.getId());
        assertEquals("city.Test", ciudadTest.getCity());
        assertNull(ciudadTest.getProducts());
    }

    @Test
    void getIdTest() {
        Ciudad ciudadTest = new Ciudad();
        Long idValue = 4L;
        ciudadTest.setId(idValue);
        assertEquals(idValue, ciudadTest.getId());
    }

    @Test
    void getCityTest() {
        Ciudad ciudadTest = new Ciudad();
        String cityValue = "city.Test";
        ciudadTest.setCity(cityValue);
        assertEquals(cityValue, ciudadTest.getCity());
    }

}