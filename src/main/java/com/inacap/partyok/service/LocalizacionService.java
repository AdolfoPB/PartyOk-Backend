package com.inacap.partyok.service;

import com.inacap.partyok.model.*;
import com.inacap.partyok.repository.IComunaRepository;
import com.inacap.partyok.repository.IProvinciaRepository;
import com.inacap.partyok.repository.IRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacionService {

    @Autowired
    private IRegionRepository iRegionRepository;
    @Autowired
    private IProvinciaRepository iProvinciaRepository;
    @Autowired
    private IComunaRepository iComunaRepository;

    public List<Region> obtenerRegiones(){
        return  this.iRegionRepository.findAll();
    }
    public List<Provincia> obtenerProvincia(Long id){
        return  this.iProvinciaRepository.findAllByRegionId(id);
    }
    public List<Comuna> obtenerComunas(Long id){
        return  this.iComunaRepository.findAllByProvinciaId(id);
    }
}
