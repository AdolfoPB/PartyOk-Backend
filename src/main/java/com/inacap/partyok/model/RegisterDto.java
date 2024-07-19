package com.inacap.partyok.model;

public class RegisterDto {
    private String correo;
    private String contrasena;
    private String rut;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String descripcion;
    private Comuna comuna;

    private Categoria categoria;

    public RegisterDto() {
    }

    public RegisterDto(String correo, String contrasena, String rut, String nombre, String primerApellido,
                       String segundoApellido, String descripcion, Comuna comuna, Categoria categoria) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.rut = rut;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.descripcion = descripcion;
        this.comuna = comuna;
        this.categoria = categoria;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
