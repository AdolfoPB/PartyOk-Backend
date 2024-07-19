package com.inacap.partyok.controller;


import com.inacap.partyok.model.Comuna;
import com.inacap.partyok.model.Provincia;
import com.inacap.partyok.model.Region;
import com.inacap.partyok.model.RegionDto;
import com.inacap.partyok.service.LocalizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localizacion")
public class LocalizacionController {

    @Autowired
    private LocalizacionService localizacionService;

    @GetMapping(value = "/region")
    public ResponseEntity<List<Region>> obtenerRegion(){
        return new ResponseEntity<>(this.localizacionService.obtenerRegiones(), HttpStatus.OK);
    }
    @GetMapping(value = "/provincia")
    public ResponseEntity<List<Provincia>> obtenerProvincia(@RequestParam Long id){
        return new ResponseEntity<>(this.localizacionService.obtenerProvincia(id), HttpStatus.OK);
    }
    @GetMapping(value = "/comuna")
    public ResponseEntity<List<Comuna>> obtenerComuna(@RequestParam Long id){
        return new ResponseEntity<>(this.localizacionService.obtenerComunas(id), HttpStatus.OK);
    }
}
