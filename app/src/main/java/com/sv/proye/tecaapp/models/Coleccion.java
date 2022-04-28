package com.sv.proye.tecaapp.models;

public class Coleccion {
    private Integer idColeccion;
    private String codigo;
    private String nombre;

    public Coleccion() {
    }

    public Coleccion(Integer idColeccion, String codigo, String nombre) {
        this.idColeccion = idColeccion;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
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
}
