package com.inacap.partyok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inacap.partyok.model.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProveedorRepository extends JpaRepository<Proveedor, Long>{

    boolean existsByRut(String rut);


    @Query("SELECT p FROM proveedor  p WHERE p.usuario.id = :id")
    Proveedor findByUserId( @Param("id") Long id);

    List<Proveedor> findAllByCategoria_Id(Long id);
}
