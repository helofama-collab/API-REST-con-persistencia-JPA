package com.example.nombreprovisionaldelproyecto.repository;

import com.example.nombreprovisionaldelproyecto.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // Módulo B: buscar por título ignorando mayúsculas
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

    // Módulo A: buscar todas las películas de un director por su ID
    List<Pelicula> findByDirectorId(Long directorId);
}
