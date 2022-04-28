package com.sv.proye.tecaapp.models;

public class LibroDeseado {
    private Integer idLibroDes;
    private Usuario usuario;
    private String codigo;
    private String nombre;

    public LibroDeseado() {
    }

    public LibroDeseado(Integer idLibroDes, Usuario usuario, String codigo, String nombre) {
        this.idLibroDes = idLibroDes;
        this.usuario = usuario;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdLibroDes() {
        return idLibroDes;
    }

    public void setIdLibroDes(Integer idLibroDes) {
        this.idLibroDes = idLibroDes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
