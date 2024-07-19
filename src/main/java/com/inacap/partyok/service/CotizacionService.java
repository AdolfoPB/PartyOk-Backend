package com.inacap.partyok.service;

import com.inacap.partyok.model.Cotizacion;
import com.inacap.partyok.repository.ICotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotizacionService {

    @Autowired
    private ICotizacionRepository iCotizacionRepositoryM;

    public Cotizacion crearCotizacion(Cotizacion cotizacion){
        return this.iCotizacionRepositoryM.save(cotizacion);
    }

    public Cotizacion actualizarCotizacion(Cotizacion cotizacion){
        return this.iCotizacionRepositoryM.save(cotizacion);
    }
    public List<Cotizacion> obtenerCotizaciones(){
        return this.iCotizacionRepositoryM.findAll();
    }

    public boolean eliminarCotizacioN(Long id){
        this.iCotizacionRepositoryM.deleteById(id);
        return true;
    }
    public List<Cotizacion> obtenerCotizacionesProveedor(Long id){
        return this.iCotizacionRepositoryM.findAllByProveedorId(id, false);
    }

}
