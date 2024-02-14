package com.example.demo.repository;

import com.example.demo.models.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICaracteristicaRepository extends JpaRepository <Caracteristica, Long> {
}
