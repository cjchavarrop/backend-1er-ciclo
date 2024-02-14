package com.example.demo.services;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.models.Categoria;
import com.example.demo.repository.ICategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaServices implements ICategoriaServices{

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarCategoria (CategoriaDTO categoriaDTO){
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        categoriaRepository.save(categoria);
    }
    @Override
    public void crearCategoria(CategoriaDTO categoriaDTO) {
        guardarCategoria(categoriaDTO);
    }

    @Override
    public Optional<CategoriaDTO> getCategoria(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        CategoriaDTO categoriaDTO = null;
        if (categoria.isPresent()){
            categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);
        }
        return Optional.ofNullable(categoriaDTO);
    }

    @Override
    public void modificarCategoria(CategoriaDTO categoriaDTO) {
        guardarCategoria(categoriaDTO);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Set<CategoriaDTO> getTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        Set<CategoriaDTO> categoriasDTO = new HashSet<>();

        for (Categoria categoria : categorias) {
            categoriasDTO.add(mapper.convertValue(categoria, CategoriaDTO.class));
        }
        return categoriasDTO;
    }
}
