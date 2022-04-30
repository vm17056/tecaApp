package com.sv.proye.tecaapp.models;

import java.util.Date;

public class Usuario {

    //    @PrimaryKey
//    @ColumnInfo(name = "IDUSUARIO")
    private Integer idUsuario;
    //    @ColumnInfo(name = "NOMBRE", typeAffinity = ColumnInfo.TEXT)
    private String nombre;
    //    @ColumnInfo(name = "APELLIDO", typeAffinity = ColumnInfo.TEXT)
    private String apellido;
    //    @ColumnInfo(name = "USERNAME", typeAffinity = ColumnInfo.TEXT)
    private String userName;
    //    @ColumnInfo(name = "USERPASS", typeAffinity = ColumnInfo.TEXT)
    private String userPass;
    //    @ColumnInfo(name = "FECHANACIMIENTO", typeAffinity = ColumnInfo.TEXT)
    private Date fechaNacimiento;
    //    @ColumnInfo(name = "TELEFONO", typeAffinity = ColumnInfo.TEXT)
    private String telefono;
    //    @ColumnInfo(name = "EMAIL", typeAffinity = ColumnInfo.TEXT)
    private String email;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombre, String apellido, String userName, String userPass, Date fechaNacimiento, String telefono, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.userName = userName;
        this.userPass = userPass;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userPass='").append(userPass).append('\'');
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
