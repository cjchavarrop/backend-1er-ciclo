package com.example.demo.services;

import com.example.demo.dto.CaracteristicaDTO;
import com.example.demo.dto.CiudadDTO;
import com.example.demo.models.Caracteristica;
import com.example.demo.models.Ciudad;
import com.example.demo.repository.ICiudadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CiudadServices implements ICiudadServices{

    @Autowired
    private ICiudadRepository ciudadRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarCiudad (CiudadDTO ciudadDTO){
        Ciudad ciudad = mapper.convertValue(ciudadDTO, Ciudad.class);
        ciudadRepository.save(ciudad);
    }

    @Override
    public void crearCiudad(CiudadDTO ciudadDTO) {
        guardarCiudad(ciudadDTO);
    }

    @Override
    public Optional<CiudadDTO> getCiudad(Long id) {
        Optional<Ciudad> ciudad = ciudadRepository.findById(id);
        CiudadDTO ciudadDTO = null;
        if (ciudad.isPresent()){
            ciudadDTO = mapper.convertValue(ciudad, CiudadDTO.class);
        }
        return Optional.ofNullable(ciudadDTO);
    }

    @Override
    public void modificarCiudad(CiudadDTO ciudadDTO) {
        guardarCiudad(ciudadDTO);
    }

    @Override
    public void eliminarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }

    @Override
    public Set<CiudadDTO> getTodos() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        Set<CiudadDTO> ciudadesDTO = new HashSet<>();

        for (Ciudad ciudad : ciudades) {
            ciudadesDTO.add(mapper.convertValue(ciudad, CiudadDTO.class));
        }
        return ciudadesDTO;
    }
}
