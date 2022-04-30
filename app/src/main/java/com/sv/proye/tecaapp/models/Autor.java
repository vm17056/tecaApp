package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Autor {

    //    @PrimaryKey
//    @ColumnInfo(name = "IDAUTOR")
    private Integer idAutor;
    //    @ColumnInfo(name = "NOMBRE", typeAffinity = ColumnInfo.TEXT)
    private String nombre;
    //    @ColumnInfo(name = "APELLIDO", typeAffinity = ColumnInfo.TEXT)
    private String apellido;
    //    @ColumnInfo(name = "NOMBRE", typeAffinity = ColumnInfo.TEXT)
    private Date fechaNacimiento;
    //    @ColumnInfo(name = "NOMBRE", typeAffinity = ColumnInfo.TEXT)
    private Boolean fallecido;
    //    @ColumnInfo(name = "NOMBRE", typeAffinity = ColumnInfo.TEXT)
    private Date fechaDefuncion;

    public Autor() {
    }

    public Autor(Integer idAutor, String nombre, String apellido, Date fechaNacimiento, Boolean fallecido, Date fechaDefuncion) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fallecido = fallecido;
        this.fechaDefuncion = fechaDefuncion;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getFallecido() {
        return fallecido;
    }

    public void setFallecido(Boolean fallecido) {
        this.fallecido = fallecido;
    }

    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

}
