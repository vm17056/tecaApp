package com.sv.proye.tecaapp.models;

public class ColeccionLibro {
    private Integer idColeccion;
    private Libro libro;
    private Coleccion coleccion;

    public ColeccionLibro() {
    }

    public ColeccionLibro(Integer idColeccion, Libro libro, Coleccion coleccion) {
        this.idColeccion = idColeccion;
        this.libro = libro;
        this.coleccion = coleccion;
    }

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }
}
