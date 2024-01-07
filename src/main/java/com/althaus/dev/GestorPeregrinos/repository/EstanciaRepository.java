package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstanciaRepository extends CoreRepository<Estancia, Long> {
    @Query("SELECT e FROM Estancia e WHERE e.parada.id = ?1 AND e.fecha BETWEEN ?2 AND ?3")
    List<Estancia> findByParadaIdAndFechaBetween(long paradaId, LocalDate fechaInicio, LocalDate fechaFin);
}
