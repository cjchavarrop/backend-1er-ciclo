package com.example.demo.controller;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.ICategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    @Autowired
    ICategoriaServices categoriaService;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<?> addCategoria(@RequestBody CategoriaDTO categoriaDTO){
        try {
            categoriaService.crearCategoria(categoriaDTO);
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable Long id) {
        Optional<CategoriaDTO> categoria = categoriaService.getCategoria(id);
        if (categoria.isPresent())
            return ResponseEntity.status(200).body(categoria);
        else
            return ResponseEntity.status(404).body("El id dado no existe");
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/")
    public ResponseEntity<?> modifyCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            if (categoriaDTO.getId() != null && categoriaService.getCategoria(categoriaDTO.getId()).isPresent()) {
                categoriaService.modificarCategoria(categoriaDTO);
                return ResponseEntity.status(200).body("OK");
            } else {
                return ResponseEntity.status(404).body("El id dado no existe");
            }
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
        try {
            Optional<CategoriaDTO> categoria = categoriaService.getCategoria(id);
            if (categoria.isPresent()) {
                categoriaService.eliminarCategoria(id);
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                return ResponseEntity.status(404).body("El id dado no existe");
            }
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/")
    public Collection<CategoriaDTO> getAllCategorias(){
        return categoriaService.getTodos();
    }
}
