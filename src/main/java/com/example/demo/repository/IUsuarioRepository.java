package com.example.demo.repository;

import com.example.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository <Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByUsername(String username);
}
