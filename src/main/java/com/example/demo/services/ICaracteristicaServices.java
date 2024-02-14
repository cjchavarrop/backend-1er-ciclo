package com.example.demo.services;

import com.example.demo.dto.CaracteristicaDTO;

import java.util.Optional;
import java.util.Set;

public interface ICaracteristicaServices {

    void crearCaracteristica(CaracteristicaDTO caracteristicaDTO);

    Optional<CaracteristicaDTO> getCaracteristica(Long id);

    void modificarCaracteristica(CaracteristicaDTO caracteristicaDTO);

    void eliminarCaracteristica(Long id);

    Set<CaracteristicaDTO> getTodos();
}
