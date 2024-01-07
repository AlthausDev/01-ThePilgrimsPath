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
 * Esta clase proporciona la implementación concreta de las operaciones
 * específicas del servicio para la entidad Estancia.
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
     * @param idParada
     * @param fechaInicio
     * @param fechaFin
     * @return
     */

    public List<Estancia> getEstanciasByParadaAndFecha(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        return estanciaRepository.findByParadaIdAndFechaBetween(idParada, fechaInicio, fechaFin);
    }
}
