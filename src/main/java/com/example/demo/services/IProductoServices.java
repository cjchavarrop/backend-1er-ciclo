package com.example.demo.services;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.ProductoSummaryDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.models.Politica;

import java.util.Optional;
import java.util.Set;

public interface IProductoServices {
    void crearProducto(ProductoDTO productoDTO);

    Optional<ProductoDTO> getProducto(Long id);

    void modificarProducto(ProductoDTO productoDTO);

    void eliminarProducto(Long id);

    Set<ProductoSummaryDTO> getTodos();

    Set<ProductoSummaryDTO> getTodosRandom();

    Set<ProductoSummaryDTO> getTodosByCategoria(Long id);

    Set<ProductoSummaryDTO> getTodosByCiudad(Long id);

    Set<ProductoSummaryDTO> getProductsByDate(String start_date, String end_date);

    Set<ProductoSummaryDTO> getProductsByDateAndCity(String start_date, String end_date, Long city_id);

    void addPolitica(Long id_producto, Long id_politica);

    void deletePolitica(Long id_producto, Long id_politica);

    void addCaracteristica(Long id_producto, Long id_caracteristica);

    void deleteCaracteristica(Long id_producto, Long id_caracteristica);

}
