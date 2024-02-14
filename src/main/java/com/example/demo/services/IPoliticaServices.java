package com.example.demo.services;

import com.example.demo.dto.PoliticaDTO;

import java.util.Optional;
import java.util.Set;

public interface IPoliticaServices {
    void crearPolitica(PoliticaDTO politicaDTO);

    Optional<PoliticaDTO> getPolitica(Long id);

    void modificarPolitica(PoliticaDTO politicaDTO);

    void eliminarPolitica(Long id);

    Set<PoliticaDTO> getTodos();
}
