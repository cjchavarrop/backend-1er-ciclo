package com.example.demo.controller;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioRegisterDTO;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.services.IUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private IUsuarioServices usuarioServices;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<?> addUsuario(@RequestBody UsuarioRegisterDTO usuarioRegisterDTO){
        try {
            usuarioServices.crearUsuario(usuarioRegisterDTO);
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        try {
            Optional<UsuarioDTO> usuario = usuarioServices.getUsuario(id);
            if (usuario.isPresent())
                return ResponseEntity.status(200).body(usuario);
            else
                return ResponseEntity.status(404).body("El id dado no existe");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/")
    public ResponseEntity<?> modifyUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            if (usuarioDTO.getId() != null && usuarioServices.getUsuario(usuarioDTO.getId()).isPresent()) {
                usuarioServices.modificarUsuario(usuarioDTO);
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
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        try {
            Optional<UsuarioDTO> usuario = usuarioServices.getUsuario(id);
            if (usuario.isPresent()) {
                usuarioServices.eliminarUsuario(id);
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
    public ResponseEntity<?> getAllUsuarios(){
        try {
            return ResponseEntity.status(200).body(usuarioServices.getTodos());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e);
        }
    }
}
