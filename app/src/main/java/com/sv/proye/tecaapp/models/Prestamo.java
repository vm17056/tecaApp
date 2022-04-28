package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Prestamo {
    private Integer idPrestamo;
    private Inventario inventario;
    private Usuario usuario;
    private Integer cantidad;
    private Date fechaPrestamo;
    private Date fechaEntrega;
    private Boolean devuelto;

    public Prestamo() {
    }

    public Prestamo(Integer idPrestamo, Inventario inventario, Usuario usuario, Integer cantidad, Date fechaPrestamo, Date fechaEntrega, Boolean devuelto) {
        this.idPrestamo = idPrestamo;
        this.inventario = inventario;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaEntrega = fechaEntrega;
        this.devuelto = devuelto;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Boolean getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(Boolean devuelto) {
        this.devuelto = devuelto;
    }
}
