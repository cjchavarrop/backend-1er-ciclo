package com.example.demo.services;

import com.example.demo.dto.CaracteristicaDTO;
import com.example.demo.models.Caracteristica;
import com.example.demo.repository.ICaracteristicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CaracteristicaServices implements ICaracteristicaServices {

    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarCaracteristica (CaracteristicaDTO caracteristicaDTO){
        Caracteristica caracteristica = mapper.convertValue(caracteristicaDTO, Caracteristica.class);
        caracteristicaRepository.save(caracteristica);
    }

    @Override
    public void crearCaracteristica(CaracteristicaDTO caracteristicaDTO) {
        guardarCaracteristica(caracteristicaDTO);
    }

    @Override
    public Optional<CaracteristicaDTO> getCaracteristica(Long id) {
        Optional<Caracteristica> caracteristica = caracteristicaRepository.findById(id);
        CaracteristicaDTO caracteristicaDTO = null;
        if (caracteristica.isPresent()){
            caracteristicaDTO = mapper.convertValue(caracteristica, CaracteristicaDTO.class);
        }
        return Optional.ofNullable(caracteristicaDTO);
    }

    @Override
    public void modificarCaracteristica(CaracteristicaDTO caracteristicaDTO) {
        guardarCaracteristica(caracteristicaDTO);
    }

    @Override
    public void eliminarCaracteristica(Long id) {
        caracteristicaRepository.deleteById(id);
    }

    @Override
    public Set<CaracteristicaDTO> getTodos() {
        List<Caracteristica> caracteristicas = caracteristicaRepository.findAll();
        Set<CaracteristicaDTO> caracteristicasDTO = new HashSet<>();

        for (Caracteristica caracteristica : caracteristicas) {
            caracteristicasDTO.add(mapper.convertValue(caracteristica, CaracteristicaDTO.class));
        }
        return caracteristicasDTO;
    }
}
