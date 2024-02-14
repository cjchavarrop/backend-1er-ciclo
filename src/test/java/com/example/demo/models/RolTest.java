package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RolTest {

    @Test

    void validateConstructor() {
        Rol rol = new Rol();
        assertNotNull(rol);
    }

    @Test

    void settersAndGettersTest() {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setName("name");
        assertEquals(1L, rol.getId());
        assertEquals("name", rol.getName());
    }


}