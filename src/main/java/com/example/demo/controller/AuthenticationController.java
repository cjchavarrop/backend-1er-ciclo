package com.example.demo.controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.models.Usuario;
import com.example.demo.models.authentication.AuthenticationRequest;
import com.example.demo.services.UsuarioServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    ObjectMapper mapper;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        Usuario usuario = usuarioServices.getUsuarioByUsername(userDetails.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);
        response.put("user_id", usuario.getId().toString());
        response.put("given_name", usuario.getGiven_name());
        response.put("sur_name", usuario.getSur_name());
        response.put("rol", usuario.getRol().getName());

        return ResponseEntity.status(200).body(response);
    }
}
