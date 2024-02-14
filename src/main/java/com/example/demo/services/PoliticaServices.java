package com.example.demo.services;

import com.example.demo.dto.PoliticaDTO;
import com.example.demo.models.Politica;
import com.example.demo.repository.IPoliticaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PoliticaServices implements IPoliticaServices{

    @Autowired
    private IPoliticaRepository politicaRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarPolitica (PoliticaDTO politicaDTO){
        Politica politica = mapper.convertValue(politicaDTO, Politica.class);
        politicaRepository.save(politica);
    }

    @Override
    public void crearPolitica(PoliticaDTO politicaDTO) {
        guardarPolitica(politicaDTO);
    }

    @Override
    public Optional<PoliticaDTO> getPolitica(Long id) {
        Optional<Politica> politica = politicaRepository.findById(id);
        PoliticaDTO politicaDTO = null;
        if (politica.isPresent()){
            politicaDTO = mapper.convertValue(politica, PoliticaDTO.class);
        }
        return Optional.ofNullable(politicaDTO);
    }

    @Override
    public void modificarPolitica(PoliticaDTO politicaDTO) {
        guardarPolitica(politicaDTO);
    }

    @Override
    public void eliminarPolitica(Long id) {
        politicaRepository.deleteById(id);
    }

    @Override
    public Set<PoliticaDTO> getTodos() {
        List<Politica> politicas = politicaRepository.findAll();
        Set<PoliticaDTO> politicasDTO = new HashSet<>();

        for (Politica politica : politicas) {
            politicasDTO.add(mapper.convertValue(politica, PoliticaDTO.class));
        }
        return politicasDTO;
    }
}
