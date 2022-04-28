package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Libro {
    private Integer idLibro;
    private Autor autor;
    private String codigo;
    private String nombre;
    private Date fechaPublicacion;

    public Libro() {
    }

    public Libro(Integer idLibro, Autor autor, String codigo, String nombre, Date fechaPublicacion) {
        this.idLibro = idLibro;
        this.autor = autor;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
