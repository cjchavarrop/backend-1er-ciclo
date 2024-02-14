package com.example.demo.controller;

import com.example.demo.dto.CaracteristicaDTO;
import com.example.demo.services.ICaracteristicaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/features")
public class CaracteristicaController {

    @Autowired
    ICaracteristicaServices caracteristicaServices;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<?> addCaracteristica(@RequestBody CaracteristicaDTO caracteristicaDTO){
        try {
            caracteristicaServices.crearCaracteristica(caracteristicaDTO);
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCaracteristica(@PathVariable Long id) {
        Optional<CaracteristicaDTO> caracteristica = caracteristicaServices.getCaracteristica(id);
        if (caracteristica.isPresent())
            return ResponseEntity.status(200).body(caracteristica);
        else
            return ResponseEntity.status(404).body("El id dado no existe");
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/")
    public ResponseEntity<?> modifyCaracteristica(@RequestBody CaracteristicaDTO caracteristicaDTO) {
        try {
            if (caracteristicaDTO.getId() != null && caracteristicaServices.getCaracteristica(caracteristicaDTO.getId()).isPresent()) {
                caracteristicaServices.modificarCaracteristica(caracteristicaDTO);
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
    public ResponseEntity<?> deleteCaracteristica(@PathVariable Long id){
        try {
            Optional<CaracteristicaDTO> caracteristica = caracteristicaServices.getCaracteristica(id);
            if (caracteristica.isPresent()) {
                caracteristicaServices.eliminarCaracteristica(id);
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
    public Collection<CaracteristicaDTO> getAllCaracteristicas(){
        return caracteristicaServices.getTodos();
    }
}
