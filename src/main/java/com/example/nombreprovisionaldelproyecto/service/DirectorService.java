package com.example.nombreprovisionaldelproyecto.service;

import com.example.nombreprovisionaldelproyecto.model.Director;
import com.example.nombreprovisionaldelproyecto.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    private final DirectorRepository repository;

    public DirectorService(DirectorRepository repository) {
        this.repository = repository;
    }

    // Devuelve todos los directores
    public List<Director> obtenerTodos() {
        return repository.findAll();
    }

    // Busca un director por ID
    public Optional<Director> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // Crea o actualiza un director
    public Director guardar(Director director) {
        return repository.save(director);
    }

    // Borra un director comprobando si existe
    public boolean borrar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}