package com.example.demo.services;

import com.example.demo.dto.PoliticaDTO;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.ProductoSummaryDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.models.Caracteristica;
import com.example.demo.models.Politica;
import com.example.demo.models.Producto;
import com.example.demo.models.Usuario;
import com.example.demo.repository.ICaracteristicaRepository;
import com.example.demo.repository.IPoliticaRepository;
import com.example.demo.repository.IProductoRepository;
import com.example.demo.repository.IUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductoServices implements IProductoServices{

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IPoliticaRepository politicaRepository;

    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IUsuarioServices usuarioServices;

    @Autowired
    ObjectMapper mapper;

    private void guardarProducto (ProductoDTO productoDTO){
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        productoRepository.save(producto);
    }

    @Override
    public void crearProducto(ProductoDTO productoDTO) {
        guardarProducto(productoDTO);
    }

    @Override
    public Optional<ProductoDTO> getProducto(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        ProductoDTO productoDTO = null;
        if (producto.isPresent()){
            productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        }
        return Optional.ofNullable(productoDTO);
    }

    @Override
    public void modificarProducto(ProductoDTO productoDTO) {
        guardarProducto(productoDTO);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Set<ProductoSummaryDTO> getTodos() {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoSummaryDTO> productosSummaryDTO = new HashSet<>();

        for (Producto producto : productos) {
            productosSummaryDTO.add(mapper.convertValue(producto, ProductoSummaryDTO.class));
        }
        return productosSummaryDTO;
    }

    @Override
    public Set<ProductoSummaryDTO> getTodosRandom() {
        List<Producto> productos = productoRepository.getProductsRandom();
        Set<ProductoSummaryDTO> productosSummaryDTO = new HashSet<>();

        for (Producto producto : productos) {
            productosSummaryDTO.add(mapper.convertValue(producto, ProductoSummaryDTO.class));
        }
        return productosSummaryDTO;
    }

    @Override
    public Set<ProductoSummaryDTO> getTodosByCategoria(Long id) {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoSummaryDTO> productosSummaryDTO = new HashSet<>();
        for (Producto producto : productos) {
            if (producto.getCategory().getId() == id){
                productosSummaryDTO.add(mapper.convertValue(producto, ProductoSummaryDTO.class));
            }
        }
        return productosSummaryDTO;
    }

    @Override
    public Set<ProductoSummaryDTO> getTodosByCiudad(Long id) {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoSummaryDTO> productosSummaryDTO = new HashSet<>();
        for (Producto producto : productos) {
            if (producto.getLocation().getId() == id){
                productosSummaryDTO.add(mapper.convertValue(producto, ProductoSummaryDTO.class));
            }
        }
        return productosSummaryDTO;
    }

    @Override
    public Set<ProductoSummaryDTO> getProductsByDate(String start_date, String end_date) {
        List<Producto> productos = productoRepository.getProductsWithoutBook(start_date, end_date);
        Set<ProductoSummaryDTO> productosSummaryDTO = new HashSet<>();

        for (Producto producto : productos) {
            productosSummaryDTO.add(mapper.convertValue(producto, ProductoSummaryDTO.class));
        }
        return productosSummaryDTO;
    }

    @Override
    public Set<ProductoSummaryDTO> getProductsByDateAndCity(String start_date, String end_date, Long city_id) {
        List<Producto> productos = productoRepository.getProductsWithoutBookAndCity(start_date, end_date, city_id);
        Set<ProductoSummaryDTO> productosSummaryDTO = new HashSet<>();

        for (Producto producto : productos) {
            productosSummaryDTO.add(mapper.convertValue(producto, ProductoSummaryDTO.class));
        }
        return productosSummaryDTO;
    }

    @Override
    public void addPolitica(Long id_producto, Long politicaRequest) {

        Politica politica_bind = politicaRepository.findById(politicaRequest).orElseThrow();
        if (politica_bind.getId() != null){
            Optional<Object> politica = productoRepository.findById(id_producto).map(producto -> {
                long politicaId = politica_bind.getId();

                // politica si existe
                if (politicaId != 0L) {
                    Politica _politica = politicaRepository.findById(politicaId).orElseThrow();
                    producto.addPolitica(_politica);
                    productoRepository.save(producto);
                    return _politica;
                }

                producto.addPolitica(politica_bind);
                return politicaRepository.save(politica_bind);
            });
        }
    }

    @Override
    public void deletePolitica(Long id_producto, Long id_politica) {
        Producto producto = productoRepository.findById(id_producto).orElseThrow();
        producto.removePolitica(id_politica);
        productoRepository.save(producto);
    }

    @Override
    public void addCaracteristica(Long id_producto, Long caracteristicaRequest) {
        Caracteristica caracteristica_bind = caracteristicaRepository.findById(caracteristicaRequest).orElseThrow();
        if (caracteristica_bind.getId() != null){
            Optional<Object> caracteristica = productoRepository.findById(id_producto).map(producto -> {
                long caracteristicaId = caracteristica_bind.getId();

                // politica si existe
                if (caracteristicaId != 0L) {
                    Caracteristica _caracteristica = caracteristicaRepository.findById(caracteristicaId).orElseThrow();
                    producto.addCaracteristica(_caracteristica);
                    productoRepository.save(producto);
                    return _caracteristica;
                }

                producto.addCaracteristica(caracteristica_bind);
                return caracteristicaRepository.save(caracteristica_bind);
            });
        }
    }

    @Override
    public void deleteCaracteristica(Long id_producto, Long id_caracteristica) {
        Producto producto = productoRepository.findById(id_producto).orElseThrow();
        producto.removeCaracteristica(id_caracteristica);
        productoRepository.save(producto);
    }
}
