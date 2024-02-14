package com.example.demo.repository;

import com.example.demo.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository <Categoria, Long> {
}
