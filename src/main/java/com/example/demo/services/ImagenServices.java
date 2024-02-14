package com.example.demo.services;

import com.example.demo.dto.ImagenDTO;
import com.example.demo.models.Imagen;
import com.example.demo.models.Producto;
import com.example.demo.repository.IImagenRepository;
import com.example.demo.repository.IPoliticaRepository;
import com.example.demo.repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImagenServices implements IImagenServices{

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;

    private void guardarImagen (ImagenDTO imagenDTO, Long id_producto){
        Producto producto = productoRepository.findById(id_producto).orElse(null);
        Imagen imagen = mapper.convertValue(imagenDTO, Imagen.class);
        imagen.setProduct(producto);
        imagenRepository.save(imagen);
    }

    @Override
    public void crearImagen(ImagenDTO imagenDTO, Long id_producto) {
        guardarImagen(imagenDTO, id_producto);
    }

    @Override
    public void crearImagenes(Imagen imagen_, Long id_producto) {
        Producto producto = productoRepository.findById(id_producto).orElse(null);
        Imagen imagen = imagen_;
        imagen.setProduct(producto);
        imagenRepository.save(imagen);
    }

    @Override
    public Optional<ImagenDTO> getImagen(Long id) {
        Optional<Imagen> imagen = imagenRepository.findById(id);
        ImagenDTO imagenDTO = null;
        if (imagen.isPresent()){
            imagenDTO = mapper.convertValue(imagen, ImagenDTO.class);
        }
        return Optional.ofNullable(imagenDTO);
    }

    @Override
    public void modificarImagen(ImagenDTO imagenDTO, Long id_producto) {
        guardarImagen(imagenDTO, id_producto);
    }

    @Override
    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }

    @Override
    public Set<ImagenDTO> getTodos() {
        List<Imagen> imagenes = imagenRepository.findAll();
        Set<ImagenDTO> imagenesDTO = new HashSet<>();

        for (Imagen imagen : imagenes) {
            imagenesDTO.add(mapper.convertValue(imagen, ImagenDTO.class));
        }
        return imagenesDTO;
    }
}
