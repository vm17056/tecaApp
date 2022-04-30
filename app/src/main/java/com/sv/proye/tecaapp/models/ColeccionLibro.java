package com.sv.proye.tecaapp.models;

//@Entity
public class ColeccionLibro {

    //    @PrimaryKey
//    @ColumnInfo(name = "IDCOLECCION")
    private Integer idColeccion;
    //    @Relation(entity = Libro.class, parentColumn = "LIBRO", entityColumn = "IDLIBRO")
//    @ColumnInfo(name = "LIBRO")
    private Libro libro;
    //    @Relation(entity = Coleccion.class, parentColumn = "COLECCION", entityColumn = "IDCOLECCION")
//    @ColumnInfo(name = "COLECCION")
    private Coleccion coleccion;

    public ColeccionLibro() {
    }

    public ColeccionLibro(Integer idColeccion, Libro libro, Coleccion coleccion) {
        this.idColeccion = idColeccion;
        this.libro = libro;
        this.coleccion = coleccion;
    }

    public Integer getIdColeccion() {
        return idColeccion;
    }

    public void setIdColeccion(Integer idColeccion) {
        this.idColeccion = idColeccion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColeccionLibro{");
        sb.append("idColeccion=").append(idColeccion);
        sb.append(", libro=").append(libro);
        sb.append(", coleccion=").append(coleccion);
        sb.append('}');
        return sb.toString();
    }
}
