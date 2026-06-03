package com.example.nombreprovisionaldelproyecto.controller;

import com.example.nombreprovisionaldelproyecto.model.Pelicula;
import com.example.nombreprovisionaldelproyecto.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    private final PeliculaService service;

    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    // GET /api/v1/peliculas — Devuelve todas las películas
    @GetMapping
    public ResponseEntity<List<Pelicula>> listarTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    // GET /api/v1/peliculas/{id} — Devuelve una película por ID o 404
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/peliculas — Crea una nueva película
    @PostMapping
    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula pelicula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(pelicula));
    }

    // PUT /api/v1/peliculas/{id} — Actualiza una película existente o 404
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula peliculaDatos) {
        return service.obtenerPorId(id)
                .map(peliculaExistente -> {
                    peliculaExistente.setTitulo(peliculaDatos.getTitulo());
                    peliculaExistente.setDirector(peliculaDatos.getDirector());
                    peliculaExistente.setAnyoEstreno(peliculaDatos.getAnyoEstreno());
                    peliculaExistente.setGenero(peliculaDatos.getGenero());
                    return ResponseEntity.ok(service.guardar(peliculaExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/peliculas/{id} — Borra una película o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.borrar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/v1/peliculas/buscar?titulo=xxx — Módulo B: búsqueda por título
    @GetMapping("/buscar")
    public ResponseEntity<List<Pelicula>> buscarPorTitulo(@RequestParam(required = false) String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return ResponseEntity.ok(service.obtenerTodas());
        }
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }
}