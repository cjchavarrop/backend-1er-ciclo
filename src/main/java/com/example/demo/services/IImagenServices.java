package com.example.demo.services;

import com.example.demo.dto.ImagenDTO;
import com.example.demo.models.Imagen;

import java.util.Optional;
import java.util.Set;

public interface IImagenServices {
    void crearImagen(ImagenDTO imagenDTO, Long id_producto);

    void crearImagenes(Imagen imagen_, Long id_producto);

    Optional<ImagenDTO> getImagen(Long id);

    void modificarImagen(ImagenDTO imagenDTO, Long id_producto);

    void eliminarImagen(Long id);

    Set<ImagenDTO> getTodos();
}
