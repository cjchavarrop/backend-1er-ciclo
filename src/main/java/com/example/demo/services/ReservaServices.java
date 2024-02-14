package com.example.demo.services;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.ProductoSummaryDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.models.Producto;
import com.example.demo.models.Reserva;
import com.example.demo.models.Usuario;
import com.example.demo.repository.IReservaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaServices implements IReservaServices{

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private IUsuarioServices usuarioServices;

    @Autowired
    private IProductoServices productoServices;

    private LocalDate parseDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        return date;
    }


    @Override
    public void crearReserva(Long id_producto, String token, ReservaDTO reservaDTO) throws Exception {
        Long user_id = usuarioServices.getUserIdOfToken(token);
        Optional<UsuarioDTO> usuarioDTO = usuarioServices.getUsuario(user_id);
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        Optional<ProductoDTO> productoDTO = productoServices.getProducto(id_producto);
        Producto producto = mapper.convertValue(productoDTO, Producto.class);

        Reserva check = reservaRepository.checkIfHasNoOtherBooking(reservaDTO.getStart_date(),reservaDTO.getEnd_date(),producto.getId());

        if (check instanceof Reserva == false){
            Reserva reserva = new Reserva();
            reserva.setProduct(producto);
            reserva.setUser(usuario);
            reserva.setStart_date(parseDate(reservaDTO.getStart_date()).atStartOfDay());
            reserva.setEnd_date(parseDate(reservaDTO.getEnd_date()).atStartOfDay());

            reservaRepository.save(reserva);
        } else {
            throw new Exception("Existe una reserva para las fechas escogidas");
        }
    }

    @Override
    public Set<ReservaDTO> getReservaByUser(Long id_usuario) {
        List<Reserva> reservas = reservaRepository.getBookingByUser(id_usuario);
        Set<ReservaDTO> reservasDTO = new HashSet<>();

        for (Reserva reserva : reservas) {
            reservasDTO.add(mapper.convertValue(reserva, ReservaDTO.class));
        }
        return reservasDTO;
    }

    @Override
    public void modificarReserva(Long id_producto, String token, ReservaDTO reservaDTO) {

    }

    @Override
    public void eliminarReserva(Long id) {

    }
}
