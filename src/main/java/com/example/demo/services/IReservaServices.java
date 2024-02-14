package com.example.demo.services;

import com.example.demo.dto.ProductoSummaryDTO;
import com.example.demo.dto.ReservaDTO;

import java.util.Optional;
import java.util.Set;

public interface IReservaServices {

    void crearReserva(Long id_producto, String token, ReservaDTO reservaDTO) throws Exception;

    Set<ReservaDTO> getReservaByUser(Long id_usuario);

    void modificarReserva(Long id_producto, String token, ReservaDTO reservaDTO);

    void eliminarReserva(Long id);
}
