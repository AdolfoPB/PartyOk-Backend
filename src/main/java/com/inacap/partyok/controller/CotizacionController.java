package com.inacap.partyok.controller;

import com.inacap.partyok.model.Cotizacion;
import com.inacap.partyok.service.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizacion")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;


    @PostMapping(value = "/")
    public ResponseEntity<Cotizacion> crear(@RequestBody Cotizacion cotizacion){
        return new ResponseEntity<>(this.cotizacionService.crearCotizacion(cotizacion), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Cotizacion> actualizar(@RequestBody Cotizacion cotizacion){
        return new ResponseEntity<>(this.cotizacionService.actualizarCotizacion(cotizacion), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Cotizacion>> obtener(){
        return new ResponseEntity<>(this.cotizacionService.obtenerCotizaciones(), HttpStatus.OK);
    }
    @GetMapping(value = "/proveedor")
    public ResponseEntity<List<Cotizacion>> obtenerPorProveedor(@RequestParam Long id){
        return new ResponseEntity<>(this.cotizacionService.obtenerCotizacionesProveedor(id), HttpStatus.OK);
    }
}
