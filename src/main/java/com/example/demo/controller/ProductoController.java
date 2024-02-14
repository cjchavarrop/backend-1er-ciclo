package com.example.demo.controller;

import com.example.demo.dto.PoliticaDTO;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.ProductoSummaryDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.models.Politica;
import com.example.demo.services.IPoliticaServices;
import com.example.demo.services.IProductoServices;
import com.example.demo.services.IReservaServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private IProductoServices productoServices;

    @Autowired
    private IReservaServices reservaServices;

    @Autowired
    ObjectMapper mapper;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public ResponseEntity<?> addProducto(@RequestBody ProductoDTO productoDTO){
        try {
            productoServices.crearProducto(productoDTO);
            return ResponseEntity.status(202).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getCause().getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Long id) {
        Optional<ProductoDTO> producto = productoServices.getProducto(id);
        if (producto.isPresent())
            return ResponseEntity.status(200).body(producto);
        else
            return ResponseEntity.status(404).body("El id dado no existe");
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/")
    public ResponseEntity<?> modifyProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            if (productoDTO.getId() != null && productoServices.getProducto(productoDTO.getId()).isPresent()) {
                productoServices.modificarProducto(productoDTO);
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
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        try {
            Optional<ProductoDTO> producto = productoServices.getProducto(id);
            if (producto.isPresent()) {
                productoServices.eliminarProducto(id);
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
    public ResponseEntity<?> getAllProductos(){
        try{
            return ResponseEntity.status(200).body(productoServices.getTodos());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/random/")
    public ResponseEntity<?> getAllProductosRandom(){
        try {
            return ResponseEntity.status(200).body(productoServices.getTodosRandom());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/category/{id}")
    public ResponseEntity<?> getAllProductosByCategoria(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(productoServices.getTodosByCategoria(id));
        } catch (Exception e){
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/city/{id}")
    public ResponseEntity<?> getAllProductosByCiudad(@PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(productoServices.getTodosByCiudad(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/{id}/regulation/{id_politica}")
    public ResponseEntity<?> addPolitica(@PathVariable Long id, @PathVariable Long id_politica){
        try {
            productoServices.addPolitica(id, id_politica);
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error, no se ingreso la politica");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/{id}/regulation/")
    public ResponseEntity<?> addPoliticas(@RequestBody ArrayList<Long> ids, @PathVariable Long id){
        try {
            for (Long id_pol : ids){
                //Long id_politica = mapper.convertValue(id_pol, Long.class);
                productoServices.addPolitica(id, id_pol);
            }
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error, no se ingreso alguna politica");
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}/regulation/{id_politica}")
    public ResponseEntity<?> deletePolitica(@PathVariable Long id, @PathVariable Long id_politica){
        try {
            productoServices.deletePolitica(id, id_politica);
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e){
            return ResponseEntity.status(400).body("Error, no se elimino la politica");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/{id}/feature/{id_catacteristica}")
    public ResponseEntity<?> addCaracteristica(@PathVariable Long id, @PathVariable Long id_catacteristica){
        try {
            productoServices.addCaracteristica(id, id_catacteristica);
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error, no se ingreso la caracteristica");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/{id}/feature/")
    public ResponseEntity<?> addCaracteristicas(@RequestBody ArrayList<Long> ids, @PathVariable Long id){
        try {
            for (Long id_car : ids){
                productoServices.addCaracteristica(id, id_car);
            }
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error, no se ingreso alguna caracteristica");
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}/feature/{id_catacteristica}")
    public ResponseEntity<?> deleteCaracteristica(@PathVariable Long id, @PathVariable Long id_catacteristica){
        try {
            productoServices.deleteCaracteristica(id, id_catacteristica);
            return ResponseEntity.status(201).body("OK");
        } catch (Exception e){
            return ResponseEntity.status(400).body("Error, no se elimino la caracteristica");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/{id}/book")
    public ResponseEntity<?> reserva(@PathVariable Long id, @RequestHeader (name="Authorization") String token, @RequestBody ReservaDTO reservaDTO) {
        try {
            reservaServices.crearReserva(id,token,reservaDTO);
            return ResponseEntity.status(200).body("OK");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/date/")
    public ResponseEntity<?> getAllProductosByDate(@RequestParam String from, @RequestParam String to){
        try {
            return ResponseEntity.status(200).body(productoServices.getProductsByDate(from, to));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/city/{id}/date/")
    public ResponseEntity<?> getAllProductosByDateAndCity(@RequestParam String from, @RequestParam String to, @PathVariable Long id){
        try {
            return ResponseEntity.status(200).body(productoServices.getProductsByDateAndCity(from, to, id));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
