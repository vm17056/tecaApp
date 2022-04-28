package com.sv.proye.tecaapp.models;

public class Favorito {
    private Integer idFavorito;
    private Usuario usuario;
    private Libro libro;

    public Favorito() {
    }

    public Favorito(Integer idFavorito, Usuario usuario, Libro libro) {
        this.idFavorito = idFavorito;
        this.usuario = usuario;
        this.libro = libro;
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
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
