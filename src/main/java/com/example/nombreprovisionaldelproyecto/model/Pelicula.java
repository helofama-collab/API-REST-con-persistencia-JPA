package com.example.nombreprovisionaldelproyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int anyoEstreno;

    @Column(nullable = false)
    private String genero;

    // Relación ManyToOne: muchas películas pertenecen a un director
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    @JsonIgnoreProperties("peliculas")
    private Director director;

    // Constructor vacío (obligatorio para JPA)
    public Pelicula() {}

    public Pelicula(String titulo, Director director, int anyoEstreno, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anyoEstreno = anyoEstreno;
        this.genero = genero;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Director getDirector() { return director; }
    public int getAnyoEstreno() { return anyoEstreno; }
    public String getGenero() { return genero; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDirector(Director director) { this.director = director; }
    public void setAnyoEstreno(int anyoEstreno) { this.anyoEstreno = anyoEstreno; }
    public void setGenero(String genero) { this.genero = genero; }
}