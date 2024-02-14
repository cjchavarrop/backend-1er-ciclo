package com.example.demo.controller;

import com.example.demo.dto.ImagenDTO;
import com.example.demo.services.IImagenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class ImagenController {

    @Autowired
    IImagenServices imagenServices;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/product/{id}")
    public ResponseEntity<?> addImagen(@RequestBody ImagenDTO imagenDTO, @PathVariable Long id){
        try {
            imagenServices.crearImagen(imagenDTO, id);
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/products/{id}")
    public ResponseEntity<?> addSeveralImagenes(@RequestBody ArrayList<String> urls, @PathVariable Long id){
        System.out.println(urls);
        try {
            for (String url : urls) {
                ImagenDTO imagenDTO = new ImagenDTO();
                UUID uuid = UUID.randomUUID();
                imagenDTO.setTitle(String.valueOf(uuid));
                imagenDTO.setUrl(url);
                imagenServices.crearImagen(imagenDTO, id);
            }
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getImagen(@PathVariable Long id) {
        Optional<ImagenDTO> imagen = imagenServices.getImagen(id);
        if (imagen.isPresent())
            return ResponseEntity.status(200).body(imagen);
        else
            return ResponseEntity.status(404).body("El id dado no existe");
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/product/{id}")
    public ResponseEntity<?> modifyImagen(@RequestBody ImagenDTO imagenDTO, @PathVariable Long id) {
        try {
            if (imagenDTO.getId() != null && imagenServices.getImagen(imagenDTO.getId()).isPresent()) {
                imagenServices.modificarImagen(imagenDTO, id);
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
            Optional<ImagenDTO> imagen = imagenServices.getImagen(id);
            if (imagen.isPresent()) {
                imagenServices.eliminarImagen(id);
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
    public Collection<ImagenDTO> getAllImagenes(){
        return imagenServices.getTodos();
    }
}
