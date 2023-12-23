package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
}