package com.inacap.partyok.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class RegionDto {


    private Long id;

    private String descripcion;

    private String abreviatura;

    private String capital;

    private List<ProvinciaDto> provinciaDto;

    public RegionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<ProvinciaDto> getProvinciaDto() {
        return provinciaDto;
    }

    public void setProvinciaDto(List<ProvinciaDto> provinciaDto) {
        this.provinciaDto = provinciaDto;
    }
}
