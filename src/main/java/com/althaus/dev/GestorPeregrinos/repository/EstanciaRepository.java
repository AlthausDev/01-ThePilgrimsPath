package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz de repositorio para la entidad Estancia.
 *
 * <p>
 * Esta interfaz proporciona métodos de consulta específicos para la entidad Estancia,
 * incluyendo una consulta personalizada para buscar estancias por ID de parada y rango de fechas.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @see Estancia
 */
@Repository
public interface EstanciaRepository extends CoreRepository<Estancia, Long> {

    /**
     * Busca estancias por ID de parada y rango de fechas.
     *
     * @param paradaId     ID de la parada.
     * @param fechaInicio  Fecha de inicio del rango.
     * @param fechaFin     Fecha de fin del rango.
     * @return Lista de estancias que cumplen con los criterios de búsqueda.
     */
    @Query("SELECT e FROM Estancia e WHERE e.parada.id = ?1 AND e.fecha BETWEEN ?2 AND ?3")
    List<Estancia> findByParadaIdAndFechaBetween(long paradaId, LocalDate fechaInicio, LocalDate fechaFin);
}
