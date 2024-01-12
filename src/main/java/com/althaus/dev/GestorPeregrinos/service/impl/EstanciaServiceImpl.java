package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import com.althaus.dev.GestorPeregrinos.repository.EstanciaRepository;
import com.althaus.dev.GestorPeregrinos.service.EstanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementación del servicio para la gestión de Estancias.
 *
 * <p>Esta clase proporciona la implementación concreta de las operaciones específicas del servicio para la entidad Estancia.</p>
 *
 * <p>La clase utiliza un repositorio JPA ({@link EstanciaRepository}) para acceder a la capa de persistencia de la entidad {@link Estancia}.</p>
 *
 * <p>El servicio incluye la capacidad de obtener la lista de estancias para una parada específica y en un rango de fechas dado.</p>
 *
 * @author Althaus_Dev
 * @see EstanciaService
 * @see CoreServiceImpl
 * @see Estancia
 * @see EstanciaRepository
 * @since 2024-01-12
 */
@Service
public class EstanciaServiceImpl extends CoreServiceImpl<Estancia> implements EstanciaService {

    private final EstanciaRepository estanciaRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio de Estancia.
     *
     * @param estanciaRepository El repositorio JPA utilizado para acceder a la capa de persistencia de Estancia.
     */
    @Autowired
    public EstanciaServiceImpl(EstanciaRepository estanciaRepository) {
        super(estanciaRepository);
        this.estanciaRepository = estanciaRepository;
    }

    /**
     * Obtiene la lista de estancias para una parada específica y en un rango de fechas dado.
     *
     * @param idParada     El identificador de la parada.
     * @param fechaInicio  La fecha de inicio del rango.
     * @param fechaFin     La fecha de fin del rango.
     * @return La lista de estancias para la parada y rango de fechas proporcionados.
     */
    public List<Estancia> getEstanciasByParadaAndFecha(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        return estanciaRepository.findByParadaIdAndFechaBetween(idParada, fechaInicio, fechaFin);
    }
}
