package com.sv.proye.tecaapp.models;

public class HistorialLeido {
    private Integer idHistorial;
    private Usuario usuario;
    private Libro libro;

    public HistorialLeido() {
    }

    public HistorialLeido(Integer idHistorial, Usuario usuario, Libro libro) {
        this.idHistorial = idHistorial;
        this.usuario = usuario;
        this.libro = libro;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
