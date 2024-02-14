package com.example.demo.controller;

import com.example.demo.dto.PoliticaDTO;
import com.example.demo.services.IPoliticaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/regulations")
public class PoliticaController {

    @Autowired
    private IPoliticaServices politicaServices;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<?> addPolitica(@RequestBody PoliticaDTO politicaDTO){
        try {
            politicaServices.crearPolitica(politicaDTO);
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPolitica(@PathVariable Long id) {
        Optional<PoliticaDTO> politica = politicaServices.getPolitica(id);
        if (politica.isPresent())
            return ResponseEntity.status(200).body(politica);
        else
            return ResponseEntity.status(404).body("El id dado no existe");
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/")
    public ResponseEntity<?> modifyPolitica(@RequestBody PoliticaDTO politicaDTO) {
        try {
            if (politicaDTO.getId() != null && politicaServices.getPolitica(politicaDTO.getId()).isPresent()) {
                politicaServices.modificarPolitica(politicaDTO);
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
    public ResponseEntity<?> deleteImagen(@PathVariable Long id){
        try {
            Optional<PoliticaDTO> politica = politicaServices.getPolitica(id);
            if (politica.isPresent()) {
                politicaServices.eliminarPolitica(id);
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
    public Collection<PoliticaDTO> getAllPoliticas(){
        return politicaServices.getTodos();
    }
}
