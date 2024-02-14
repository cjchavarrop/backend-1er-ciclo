package com.example.demo.services;

import com.example.demo.dto.CaracteristicaDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioRegisterDTO;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.models.Caracteristica;
import com.example.demo.models.Usuario;
import com.example.demo.repository.IUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServices implements IUsuarioServices{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    ObjectMapper mapper;

    private void guardarUsuario (UsuarioRegisterDTO usuarioRegisterDTO){
        Usuario usuario = mapper.convertValue(usuarioRegisterDTO, Usuario.class);
        usuarioRepository.save(usuario);
    }


    @Override
    public void crearUsuario(UsuarioRegisterDTO usuarioRegisterDTO) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findByUsername(usuarioRegisterDTO.getEmail());
        if (usuarioExistente instanceof Usuario) {
            throw new Exception("Ya existe ese usuario");
        } else {
            UsuarioRegisterDTO usuario = usuarioRegisterDTO;
            String password = usuario.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            usuario.setPassword(encoder.encode(password));
            guardarUsuario(usuario);
        }
    }

    @Override
    public Optional<UsuarioDTO> getUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        UsuarioDTO usuarioDTO = null;
        if (usuario.isPresent()){
            usuarioDTO = mapper.convertValue(usuario, UsuarioDTO.class);
        }
        return Optional.ofNullable(usuarioDTO);
    }

    @Override
    public Usuario getUsuarioByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario;
    }

    @Override
    public void modificarUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario_db = usuarioRepository.findById(usuarioDTO.getId());
        Usuario usuario_ = mapper.convertValue(usuario_db, Usuario.class);
        UsuarioRegisterDTO usuario1 = mapper.convertValue(usuarioDTO, UsuarioRegisterDTO.class);
        usuario1.setPassword(usuario_.getPassword());
        Usuario usuario = mapper.convertValue(usuario1, Usuario.class);
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Set<UsuarioDTO> getTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Set<UsuarioDTO> usuariosDTO = new HashSet<>();

        for (Usuario usuario : usuarios) {
            usuariosDTO.add(mapper.convertValue(usuario, UsuarioDTO.class));
        }
        return usuariosDTO;
    }

    @Override
    public Long getUserIdOfToken(String token) {
        String username= null;
        String jwt = null;
        Long response = null;

        if(token != null &&  token.startsWith("Bearer")) {
            jwt = token.substring(7);
            username = jwtUtil.extractUserName(jwt);
            Usuario usuario = usuarioRepository.findByUsername(username);
            response = usuario.getId();
        }
        return response;
    }
}
