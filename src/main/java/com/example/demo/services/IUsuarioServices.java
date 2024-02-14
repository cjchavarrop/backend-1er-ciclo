package com.example.demo.services;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioRegisterDTO;
import com.example.demo.models.Usuario;

import java.util.Optional;
import java.util.Set;

public interface IUsuarioServices {

    void crearUsuario(UsuarioRegisterDTO usuarioRegisterDTO) throws Exception;

    Optional<UsuarioDTO> getUsuario(Long id);

    Usuario getUsuarioByUsername(String username);

    void modificarUsuario(UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);

    Set<UsuarioDTO> getTodos();

    Long getUserIdOfToken(String token);
}
