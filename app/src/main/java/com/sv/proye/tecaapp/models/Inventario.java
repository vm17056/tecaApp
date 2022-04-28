package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Inventario {
    private Integer idInventario;
    private Libro libro;
    private Integer cantidad;
    private Date fechaActualizado;

    public Inventario() {
    }

    public Inventario(Integer idInventario, Libro libro, Integer cantidad, Date fechaActualizado) {
        this.idInventario = idInventario;
        this.libro = libro;
        this.cantidad = cantidad;
        this.fechaActualizado = fechaActualizado;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaActualizado() {
        return fechaActualizado;
    }

    public void setFechaActualizado(Date fechaActualizado) {
        this.fechaActualizado = fechaActualizado;
    }
}
