package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Servicio para la gestión de Estancias.
 *
 * <p>
 * Esta interfaz define las operaciones específicas para la gestión de {@link Estancia} y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.
 * </p>
 *
 * @author Althaus_Dev
 * @see Estancia
 * @see CoreService
 * @since 2024-01-12
 */
@Service
public interface EstanciaService extends CoreService<Estancia> {

    /**
     * Obtiene la lista de estancias para una parada específica y en un rango de fechas dado.
     *
     * @param idParada    ID de la parada.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin    Fecha de fin del rango.
     * @return Lista de estancias en el rango de fechas para la parada especificada.
     */
    List<Estancia> getEstanciasByParadaAndFecha(long idParada, LocalDate fechaInicio, LocalDate fechaFin);
}
