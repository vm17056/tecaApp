package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Inventario {
    //    @PrimaryKey
//    @ColumnInfo(name = "IDINVENTARIO")
    private Integer idInventario;
    //    @Relation(entity = Libro.class, parentColumn = "LIBRO", entityColumn = "IDLIBRO")
//    @ColumnInfo(name = "LIBRO")
    private Libro libro;
    //    @ColumnInfo(name = "CANTIDAD", typeAffinity = ColumnInfo.INTEGER)
    private Integer cantidad;
    //    @ColumnInfo(name = "FECHAACTUALIZADO", typeAffinity = ColumnInfo.TEXT)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Inventario{");
        sb.append("idInventario=").append(idInventario);
        sb.append(", libro=").append(libro);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", fechaActualizado=").append(fechaActualizado);
        sb.append('}');
        return sb.toString();
    }
}
