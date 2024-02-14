package com.example.demo.dto;

import com.example.demo.models.Producto;
import com.example.demo.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReservaDTO {

    private Long id;
    private String start_date;
    private String end_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
