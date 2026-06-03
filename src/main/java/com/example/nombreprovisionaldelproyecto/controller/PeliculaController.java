package com.example.nombreprovisionaldelproyecto.controller;

// Estas importaciones son las herramientas que trae Spring para que internet funcione
import com.example.nombreprovisionaldelproyecto.model.Pelicula;
import com.example.nombreprovisionaldelproyecto.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// 1. @RestController le dice a Spring: "Esta clase es la ventanilla de la API".
// Todo lo que devuelvan estos métodos se transformará automáticamente en texto JSON para internet.
@RestController

// 2. @RequestMapping define la URL base. Cualquier petición que empiece por http://localhost:8080/api/v1/peliculas vendrá aquí.
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    // Traemos al "Cerebro" (el Service). El Controller nunca toca la base de datos directamente. [cite: 80]
    private final PeliculaService service;

    // Este es el constructor. Sirve para que Spring nos inyecte (conecte) automáticamente el Service aquí dentro.
    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    /* * ==========================================
     * CRUD 1: OBTENER TODAS LAS PELÍCULAS
     * URL: GET http://localhost:8080/api/v1/peliculas
     * ==========================================
     */
    @GetMapping // @GetMapping dice: "Si alguien usa el método GET en la URL base, ejecuta esto"
    public ResponseEntity<List<Pelicula>> listarTodas() {
        // ResponseEntity es una caja donde metemos los datos y el "código de estado HTTP".
        // ResponseEntity.ok(...) mete la lista de películas dentro y le pega la etiqueta "200 OK" (Todo ha ido bien).
        return ResponseEntity.ok(service.obtenerTodas());
    }

    /* * ==========================================
     * CRUD 2: OBTENER UNA PELÍCULA POR SU ID
     * URL: GET http://localhost:8080/api/v1/peliculas/{id} (ej: /peliculas/3)
     * ==========================================
     */
    @GetMapping("/{id}") // El "{id}" significa que esa parte de la URL es variable (un número que cambia)
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        // @PathVariable le dice a Spring: "Coge el número de la URL y mételo en la variable 'id'"

        // Llamamos al service, que nos devuelve un Optional (una caja que puede tener una película o estar vacía)
        Optional<Pelicula> peliculaOpt = service.obtenerPorId(id);

        // REQUISITO MÍNIMO: Comprobamos si la película existe antes de hacer nada
        if (peliculaOpt.isPresent()) {
            // .get() abre la caja del Optional para sacar la película real
            // Devolvemos la película con un estado "200 OK"
            return ResponseEntity.ok(peliculaOpt.get());
        } else {
            // Si la caja estaba vacía, mandamos un "404 Not Found" limpio a internet [cite: 85, 88]
            // ¡Esto evita que el programa se rompa o tire un error feo!
            return ResponseEntity.notFound().build();
        }
    }

    /* * ==========================================
     * CRUD 3: CREAR UNA NUEVA PELÍCULA
     * URL: POST http://localhost:8080/api/v1/peliculas
     * ==========================================
     */
    @PostMapping // @PostMapping dice: "Si envían datos por POST a la URL, es para crear algo"
    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula pelicula) {
        // @RequestBody es crucial: Coge el JSON (el texto) que nos mandan desde Postman/internet
        // y lo convierte automáticamente en un objeto "Pelicula" de Java.

        // Le decimos al servicio que la guarde en la base de datos
        Pelicula nuevaPelicula = service.guardar(pelicula);

        // REQUISITO MÍNIMO: Al crear algo nuevo, se debe responder con el estado "201 CREATED"
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPelicula);
    }

    /* * ==========================================
     * CRUD 4: ACTUALIZAR / MODIFICAR UNA PELÍCULA
     * URL: PUT http://localhost:8080/api/v1/peliculas/{id}
     * ==========================================
     */
    @PutMapping("/{id}") // El método PUT sirve para reemplazar o editar datos existentes
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula peliculaDatos) {
        // Primero buscamos si la película que quieren modificar existe de verdad
        Optional<Pelicula> peliculaOpt = service.obtenerPorId(id);

        if (peliculaOpt.isPresent()) {
            // Si existe, la sacamos de la caja
            Pelicula peliculaExistente = peliculaOpt.get();

            // Le cambiamos sus datos viejos por los nuevos que nos acaban de pasar en el @RequestBody
            peliculaExistente.setTitulo(peliculaDatos.getTitulo());
            peliculaExistente.setDirector(peliculaDatos.getDirector());
            peliculaExistente.setAnyoEstreno(peliculaDatos.getAnyoEstreno());

            // Guardamos los cambios en la base de datos y respondemos "200 OK"
            return ResponseEntity.ok(service.guardar(peliculaExistente));
        } else {
            // Si intentan modificar una película que no existe, respondemos "404 Not Found"
            return ResponseEntity.notFound().build();
        }
    }

    /* * ==========================================
     * CRUD 5: BORRAR UNA PELÍCULA
     * URL: DELETE http://localhost:8080/api/v1/peliculas/{id}
     * ==========================================
     */
    @DeleteMapping("/{id}") // @DeleteMapping dice: "Si usan el método DELETE, es para borrar"
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Le pedimos al servicio que intente borrarla. El servicio nos dirá "true" si pudo o "false" si no existía.
        boolean borradoExitoso = service.borrar(id);

        if (borradoExitoso) {
            // REQUISITO MÍNIMO: Si se borra correctamente, se responde "204 NO CONTENT" (Significa: Borrado con éxito y no tengo nada más que enseñarte)
            return ResponseEntity.noContent().build();
        } else {
            // Si el ID no existía en la base de datos, respondemos "404 Not Found"
            return ResponseEntity.notFound().build();
        }
    }

    /* * ==========================================
     * MÓDULO B: BÚSQUEDA FILTRADA POR TÍTULO (Para nota de 8) [cite: 67, 101]
     * URL: GET http://localhost:8080/api/v1/peliculas/buscar?titulo=avatar [cite: 107]
     * ==========================================
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Pelicula>> buscarPorTitulo(@RequestParam(required = false) String titulo) {
        // @RequestParam mapea los parámetros que van después del signo "?" en la URL (ej: ?titulo=avatar) [cite: 101]
        // (required = false) es un requisito del Módulo B: Significa que poner el título es opcional. [cite: 103]

        // Si el usuario no escribe ningún título en la URL, le devolvemos TODAS las películas por defecto [cite: 103]
        if (titulo == null || titulo.isEmpty()) {
            return ResponseEntity.ok(service.obtenerTodas());
        }

        // Si escribe un título, llamamos al método especial del buscador que creamos en el Repository e ignorará mayúsculas [cite: 104]
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }
}