package com.example.demo.controller;

import com.example.demo.dto.CaracteristicaDTO;
import com.example.demo.dto.CiudadDTO;
import com.example.demo.services.ICiudadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CiudadController {

    @Autowired
    ICiudadServices ciudadServices;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<?> addCiudad(@RequestBody CiudadDTO ciudadDTO){
        try {
            ciudadServices.crearCiudad(ciudadDTO);
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCiudad(@PathVariable Long id) {
        Optional<CiudadDTO> ciudad = ciudadServices.getCiudad(id);
        if (ciudad.isPresent())
            return ResponseEntity.status(200).body(ciudad);
        else
            return ResponseEntity.status(404).body("El id dado no existe");
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/")
    public ResponseEntity<?> modifyCiudad(@RequestBody CiudadDTO ciudadDTO) {
        try {
            if (ciudadDTO.getId() != null && ciudadServices.getCiudad(ciudadDTO.getId()).isPresent()) {
                ciudadServices.modificarCiudad(ciudadDTO);
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
    public ResponseEntity<?> deleteCiudad(@PathVariable Long id){
        try {
            Optional<CiudadDTO> ciudad = ciudadServices.getCiudad(id);
            if (ciudad.isPresent()) {
                ciudadServices.eliminarCiudad(id);
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
    public Collection<CiudadDTO> getAllCiudades(){
        return ciudadServices.getTodos();
    }
}
