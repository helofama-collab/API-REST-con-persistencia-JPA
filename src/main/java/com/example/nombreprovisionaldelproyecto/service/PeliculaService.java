package com.example.nombreprovisionaldelproyecto.service;

import com.example.nombreprovisionaldelproyecto.model.Pelicula;
import com.example.nombreprovisionaldelproyecto.repository.PeliculaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository repository;

    // Conectamos el Service con la carpeta Repository
    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    // 1. Nos devuelve todas las películas
    public List<Pelicula> obtenerTodas() {
        return repository.findAll();
    }

    // 2. Buscar una película por su ID (Usa Optional por si no existe)
    public Optional<Pelicula> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o actualizar una película
    public Pelicula guardar(Pelicula pelicula) {
        return repository.save(pelicula);
    }

    // 4. Borrar una película comprobando primero si existe
    public boolean borrar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // MÓDULO B: Buscar películas por título
    public List<Pelicula> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }
}