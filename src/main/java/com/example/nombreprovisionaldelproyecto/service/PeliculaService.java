package com.example.nombreprovisionaldelproyecto.service;

import com.example.nombreprovisionaldelproyecto.model.Pelicula;
import com.example.nombreprovisionaldelproyecto.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository repository;

    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    // Devuelve todas las películas
    public List<Pelicula> obtenerTodas() {
        return repository.findAll();
    }

    // Busca una película por ID
    public Optional<Pelicula> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // Crea o actualiza una película
    public Pelicula guardar(Pelicula pelicula) {
        return repository.save(pelicula);
    }

    // Borra una película comprobando si existe
    public boolean borrar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Módulo B: buscar por título
    public List<Pelicula> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    // Módulo A: buscar películas por director
    public List<Pelicula> obtenerPorDirector(Long directorId) {
        return repository.findByDirectorId(directorId);
    }
}
