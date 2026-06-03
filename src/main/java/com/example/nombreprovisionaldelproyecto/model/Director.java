package com.example.nombreprovisionaldelproyecto.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "directores")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String nacionalidad;

    @Column(nullable = false)
    private int anyoNacimiento;

    // Un director tiene muchas películas
    @OneToMany(mappedBy = "director")
    private List<Pelicula> peliculas;

    // Constructor vacío (obligatorio para JPA)
    public Director() {}

    public Director(String nombre, String nacionalidad, int anyoNacimiento) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.anyoNacimiento = anyoNacimiento;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public int getAnyoNacimiento() { return anyoNacimiento; }
    public List<Pelicula> getPeliculas() { return peliculas; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public void setAnyoNacimiento(int anyoNacimiento) { this.anyoNacimiento = anyoNacimiento; }
    public void setPeliculas(List<Pelicula> peliculas) { this.peliculas = peliculas; }
}