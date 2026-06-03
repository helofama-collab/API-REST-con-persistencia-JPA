package com.example.nombreprovisionaldelproyecto.repository;

import com.example.nombreprovisionaldelproyecto.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // Este comando busca películas que contengan un texto en el título (ej: "star" encuentra "Star Wars")
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
}
