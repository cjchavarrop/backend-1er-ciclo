package com.example.demo.repository;

import com.example.demo.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICiudadRepository extends JpaRepository <Ciudad, Long> {
}
