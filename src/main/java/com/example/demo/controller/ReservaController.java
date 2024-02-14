package com.example.demo.controller;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.services.IReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookings")
public class ReservaController {

    @Autowired
    private IReservaServices reservaServices;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getReservaByUserId(@PathVariable Long id) {
        Collection<ReservaDTO> reservaDTOS = reservaServices.getReservaByUser(id);
        return ResponseEntity.status(200).body(reservaDTOS);
    }

}
