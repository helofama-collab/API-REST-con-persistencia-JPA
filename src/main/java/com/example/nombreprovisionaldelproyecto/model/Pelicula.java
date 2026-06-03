package com.example.nombreprovisionaldelproyecto.model;

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
    private String director;

    @Column(nullable = false)
    private int anyoEstreno;

    @Column(nullable = false)
    private String genero;

    // Constructor vacío (obligatorio para JPA)
    public Pelicula() {}

    public Pelicula(String titulo, String director, int anyoEstreno, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anyoEstreno = anyoEstreno;
        this.genero = genero;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDirector() { return director; }
    public int getAnyoEstreno() { return anyoEstreno; }
    public String getGenero() { return genero; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDirector(String director) { this.director = director; }
    public void setAnyoEstreno(int anyoEstreno) { this.anyoEstreno = anyoEstreno; }
    public void setGenero(String genero) { this.genero = genero; }
}