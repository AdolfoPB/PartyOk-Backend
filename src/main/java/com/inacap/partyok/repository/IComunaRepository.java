package com.inacap.partyok.repository;

import com.inacap.partyok.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inacap.partyok.model.Comuna;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComunaRepository extends JpaRepository<Comuna, Long>{

    List<Comuna> findAllByProvinciaId(Long id);

}
