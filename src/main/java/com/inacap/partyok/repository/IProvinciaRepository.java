package com.inacap.partyok.repository;

import com.inacap.partyok.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Long> {

    List<Provincia> findAllByRegionId(Long id);
}
