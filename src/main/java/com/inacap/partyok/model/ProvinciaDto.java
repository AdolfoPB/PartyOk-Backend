package com.inacap.partyok.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class ProvinciaDto {

    private Long id;

    private String descripcion;

    private Region region;

    private List<ComunaDto> comunaDto;

    public ProvinciaDto() {
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<ComunaDto> getComunaDto() {
        return comunaDto;
    }

    public void setComunaDto(List<ComunaDto> comunaDto) {
        this.comunaDto = comunaDto;
    }
}
