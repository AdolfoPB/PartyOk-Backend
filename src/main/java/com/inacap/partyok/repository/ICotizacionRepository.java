package com.inacap.partyok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacap.partyok.model.Cotizacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICotizacionRepository extends JpaRepository<Cotizacion, Long>{

    @Query("SELECT c FROM cotizacion c WHERE c.proveedor.id = :id AND c.eliminado = :eliminado")
    List<Cotizacion> findAllByProveedorId(@Param("id") Long id, @Param("eliminado") boolean eliminado);
}
