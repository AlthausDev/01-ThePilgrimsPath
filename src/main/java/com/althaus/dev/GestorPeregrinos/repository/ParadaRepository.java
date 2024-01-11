package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends CoreRepository<Parada, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Parada p WHERE p.nombre = :nombre")
    Boolean existsByNombre(@Param("nombre") String nombre);


}