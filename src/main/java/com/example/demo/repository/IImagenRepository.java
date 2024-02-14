package com.example.demo.repository;

import com.example.demo.models.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagenRepository extends JpaRepository <Imagen, Long> {
}
