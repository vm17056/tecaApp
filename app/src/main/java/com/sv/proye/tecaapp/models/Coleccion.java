package com.sv.proye.tecaapp.models;

//@Entity
public class Coleccion {

    //    @PrimaryKey
//    @ColumnInfo(name = "IDCOLECCION")
    private Integer idColeccion;
    //    @ColumnInfo(name = "CODIGO",typeAffinity = ColumnInfo.TEXT)
    private String codigo;
    //    @ColumnInfo(name = "NOMBRE",typeAffinity = ColumnInfo.TEXT)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coleccion{");
        sb.append("idColeccion=").append(idColeccion);
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
