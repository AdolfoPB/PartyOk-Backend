package com.inacap.partyok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inacap.partyok.security.entity.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class ProveedorDto {

    private Long id;

    private String rut;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String contacto;

    private Comuna comuna;

    private Usuario usuario;

    private Categoria categoria;

    private String imagen;

    private String descripcion;

    private BigDecimal precio;

    private Integer top;

    private Integer cantidadCotizaciones;

    @JsonIgnore
    private List<Cotizacion> cotizacions;

    public ProveedorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<Cotizacion> getCotizacions() {
        return cotizacions;
    }

    public void setCotizacions(List<Cotizacion> cotizacions) {
        this.cotizacions = cotizacions;
    }

    public Integer getTotalCotizaciones(){
        return new Integer(this.cotizacions.size());
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getCantidadCotizaciones() {
        return cantidadCotizaciones;
    }

    public void setCantidadCotizaciones(Integer cantidadCotizaciones) {
        this.cantidadCotizaciones = cantidadCotizaciones;
    }
}
