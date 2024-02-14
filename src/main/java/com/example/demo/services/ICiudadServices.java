package com.example.demo.services;

import com.example.demo.dto.CiudadDTO;

import java.util.Optional;
import java.util.Set;

public interface ICiudadServices {
    void crearCiudad(CiudadDTO ciudadDTO);

    Optional<CiudadDTO> getCiudad(Long id);

    void modificarCiudad(CiudadDTO ciudadDTO);

    void eliminarCiudad(Long id);

    Set<CiudadDTO> getTodos();
}
