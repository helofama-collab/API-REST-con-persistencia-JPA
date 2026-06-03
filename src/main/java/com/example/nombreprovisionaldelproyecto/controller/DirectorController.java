package com.example.nombreprovisionaldelproyecto.controller;

import com.example.nombreprovisionaldelproyecto.model.Director;
import com.example.nombreprovisionaldelproyecto.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/directores")
public class DirectorController {

    private final DirectorService service;

    public DirectorController(DirectorService service) {
        this.service = service;
    }

    // GET /api/v1/directores — Devuelve todos los directores
    @GetMapping
    public ResponseEntity<List<Director>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // GET /api/v1/directores/{id} — Devuelve un director por ID o 404
    @GetMapping("/{id}")
    public ResponseEntity<Director> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/directores — Crea un nuevo director
    @PostMapping
    public ResponseEntity<Director> crear(@RequestBody Director director) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(director));
    }

    // PUT /api/v1/directores/{id} — Actualiza un director existente o 404
    @PutMapping("/{id}")
    public ResponseEntity<Director> actualizar(@PathVariable Long id, @RequestBody Director directorDatos) {
        return service.obtenerPorId(id)
                .map(directorExistente -> {
                    directorExistente.setNombre(directorDatos.getNombre());
                    directorExistente.setNacionalidad(directorDatos.getNacionalidad());
                    directorExistente.setAnyoNacimiento(directorDatos.getAnyoNacimiento());
                    return ResponseEntity.ok(service.guardar(directorExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/v1/directores/{id} — Borra un director o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.borrar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}