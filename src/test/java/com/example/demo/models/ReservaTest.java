package com.example.demo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    @Test
    void getId() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        assertEquals(1L, reserva.getId());
    }

    @Test
    void validateConstructor() {
        Reserva reserva = new Reserva();
        assertNotNull(reserva);
    }


    @Test
    void getStart_date() {
        Reserva reserva = new Reserva();
        reserva.setStart_date(null);
        assertNull(reserva.getStart_date());
    }


}