package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Compra {
    private Integer idCompra;
    private Inventario inventario;
    private Integer cantidad;
    private Date fechaCompra;
    private Double precioUnitario;
    private String codigoCompra;

    public Compra() {
    }

    public Compra(Integer idCompra, Inventario inventario, Integer cantidad, Date fechaCompra, Double precioUnitario, String codigoCompra) {
        this.idCompra = idCompra;
        this.inventario = inventario;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
        this.precioUnitario = precioUnitario;
        this.codigoCompra = codigoCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }
}
