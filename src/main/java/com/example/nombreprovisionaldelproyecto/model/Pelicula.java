package com.example.nombreprovisionaldelproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    private String titulo;
    private String director; 
    private int anyoEstreno; 
    private String genero;

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, int anyoEstreno, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.anyoEstreno = anyoEstreno;
        this.genero = genero;
    }

    //Getters y Setters

    public Long getId() {
        return id;
    }

    public void gettitulo(){
        return this.titulo;
    }

    public void getdirector(){
        return this.director;
    }

    public void getgenero(){
        return this.genero;
    }

    public int getAnyoEstreno() {
        return this.anyoEstreno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void settitulo(String titulo){
        this.titulo = titulo;
    }

    public void setdirector(String director){
        this.titulo = director;
    }

    public void setgenero(String genero){
        this.titulo = genero;
    }

    public void setAnyoEstreno(int anyoEstreno) {
        this.anyoEstreno = anyoEstreno;
    }
}
